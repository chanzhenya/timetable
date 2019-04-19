package com.app.timetable.controller;


import com.app.timetable.dto.CourseDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.Picture;
import com.app.timetable.enums.CourseStatus;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    /**
     * 新增课程
     * @param tagId
     * @param price
     * @param period
     * @param descreption
     * @param teacherId
     * @param multipartFile
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagId") String tagId,@RequestParam("price")BigDecimal price, @RequestParam("period") Integer period,
                        @RequestParam("descreption") String descreption, @RequestParam("teacherId") String teacherId,
                        @RequestParam("image") MultipartFile multipartFile) {
        try {
            Picture picture = uploadFileService.uploadFile(multipartFile);
            Course course = new Course();
            course.setId(ClassObjectUtils.getUUID());
            course.setTagId(tagId);
            course.setPeriod(period);
            course.setPrice(price);
            course.setDescreption(descreption);
            course.setTeacherId(teacherId);
            course.setImgUrl(picture.getImgUrl());
            course.setStatus(CourseStatus.PUBLISHED.getCode());
            course.setCreateTime(LocalDateTime.now());
            courseService.save(course);
            return ResultVoUtil.success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
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
    public ResultVo edit(@RequestParam("price")BigDecimal price, @RequestParam("period") Integer period,
                         @RequestParam("descreption") String descreption, @RequestParam("teacherId") String teacherId,
                         @RequestParam("courseId") String courseId, @RequestParam("image") MultipartFile multipartFile) {
        try {
            Course course = courseService.getById(courseId);
            if(course != null) {
                uploadFileService.delete(course.getImgUrl());
                Picture picture = uploadFileService.uploadFile(multipartFile);
                course.setPeriod(period);
                course.setPrice(price);
                course.setDescreption(descreption);
                course.setTeacherId(teacherId);
                course.setImgUrl(picture.getImgUrl());
                course.setStatus(CourseStatus.PUBLISHED.getCode());
                courseService.updateById(course);
                return ResultVoUtil.success("更新成功");
            } else {
                return ResultVoUtil.error("课程不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
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
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            Course course = new Course();
            course.setStatus(status);
            course.setTeacherId(teacherId);
            IPage<CourseDTO> courseIPage = courseService.selectPage(pageNum,pageSize,course);
            return ResultVoUtil.success(courseIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("courseId") String courseId) {
        try {
            CourseDTO course = courseService.selectDetailById(courseId);
            return ResultVoUtil.success(course);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("courseId") String courseId) {
        try {
            courseService.removeById(courseId);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}

