package com.app.timetable.controller;


import com.app.timetable.dto.CourseDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.Picture;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.Tag;
import com.app.timetable.enums.CourseStatus;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITagService;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 新增课程
     * @param tagId
     * @param descreption
     * @param teacherId
     * @param multipartFile
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagId") String tagId,
                        @RequestParam("descreption") String descreption, @RequestParam("teacherId") String teacherId,
                        @RequestParam(value = "image",required = false) MultipartFile multipartFile) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",tagId);
        queryWrapper.eq("teacher_id",teacherId);
        List<Course> courseList = courseService.list(queryWrapper);
        if(courseList.isEmpty()) {

            Course course = new Course();
            if(multipartFile != null) {
                Picture picture = uploadFileService.uploadFile(multipartFile);
                course.setImgUrl(picture.getImgUrl());
            }
            course.setId(ClassObjectUtils.getUUID());
            course.setTagId(tagId);
            course.setDescreption(descreption);
            course.setTeacherId(teacherId);
            course.setStatus(CourseStatus.PUBLISHED.getCode());
            course.setCreateTime(LocalDateTime.now());
            courseService.save(course);
            return ResultVoUtil.success("新增成功");
        } else {
            SysUser sysUser = sysUserService.getById(teacherId);
            Tag tag = tagService.getById(tagId);
            return ResultVoUtil.error(sysUser.getName()+"老师已经在任教"+tag.getName()+"，无需再添加课程");
        }
    }

    /**
     * 修改课程
     * @param price
     * @param period
     * @param descreption
     * @param teacherId
     * @param courseId
     * @param multipartFile
     * @return
     */
    @PostMapping("/edit")
    public ResultVo edit(@RequestParam(value = "price", required = false)BigDecimal price,
                         @RequestParam(value = "period", required = false) Integer period,
                         @RequestParam(value = "descreption", required = false) String descreption,
                         @RequestParam("teacherId") String teacherId,  @RequestParam("courseId") String courseId,
                         @RequestParam(value = "image", required = false) MultipartFile multipartFile) {
        Course course = courseService.getById(courseId);
        if(course != null) {
            if(multipartFile != null) {
                uploadFileService.delete(course.getImgUrl());
                Picture picture = uploadFileService.uploadFile(multipartFile);
                course.setImgUrl(picture.getImgUrl());
            }
            if(period != null) {
                course.setPeriod(period);
            }
            if(price != null) {
                course.setPrice(price);
            }
            if(StringUtils.isNotBlank(descreption)) {
                course.setDescreption(descreption);
            }
            course.setTeacherId(teacherId);
            courseService.updateById(course);
            return ResultVoUtil.success("更新成功");
        } else {
            return ResultVoUtil.error("课程不存在");
        }
    }

    /**
     * 根据教师ID获取课程列表
     * @param teacherId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "teacherId",required = false) String teacherId,
                         @RequestParam(value = "status", required = false) Integer status,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize) {
        Course course = new Course();
        course.setStatus(status);
        course.setTeacherId(teacherId);
        IPage<CourseDTO> courseIPage = courseService.selectPage(pageNum,pageSize,course);
        return ResultVoUtil.success(courseIPage);
    }

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("courseId") String courseId) {
        CourseDTO course = courseService.selectDetailById(courseId);
        return ResultVoUtil.success(course);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("courseId") String courseId) {
        courseService.removeById(courseId);
        return ResultVoUtil.success("删除成功");
    }
}

