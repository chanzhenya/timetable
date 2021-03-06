package com.app.timetable.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.app.timetable.common.utils.BaseUtils;
import com.app.timetable.common.utils.RobotAssert;
import com.app.timetable.model.dto.CourseDTO;
import com.app.timetable.model.entity.Course;
import com.app.timetable.model.entity.Picture;
import com.app.timetable.service.*;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    private IPictureService pictureService;

    /**
     * 新增课程
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam Map<String,Object> params,
                        @RequestParam(value = "image",required = false) MultipartFile multipartFile) {
        BaseUtils.getInstance().checkParams(params,new String[]{"tagId","teacherId","descreption"});
        Course course = BeanUtil.mapToBean(params,Course.class,true);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",course.getTagId());
        queryWrapper.eq("teacher_id",course.getTeacherId());
        queryWrapper.eq("enable",1);
        List<Course> courseList = courseService.list(queryWrapper);
        RobotAssert.isEmpty(courseList,"该课程已存在，无需在添加");

        if(multipartFile != null) {
            Picture picture = pictureService.uploadFile(multipartFile);
            course.setImgUrl(picture.getImgUrl());
        }
        courseService.save(course);
        return ResultVoUtil.success("新增成功");
    }

    /**
     * 修改课程
     * @param params
     * @param multipartFile
     * @return
     */
    @PostMapping("/edit")
    public ResultVo edit(@RequestParam Map<String,Object> params,
                         @RequestParam(value = "image", required = false) MultipartFile multipartFile) {
        BaseUtils.getInstance().checkParams(params,new String[]{"courseId"});
        Course course = courseService.getById(MapUtil.getLong(params,"courseId"));
        RobotAssert.notNull(course,"找不相应的课程，请确认课程是否存在");
        if(multipartFile != null) {
            pictureService.delete(course.getImgUrl());
            Picture picture = pictureService.uploadFile(multipartFile);
            course.setImgUrl(picture.getImgUrl());
        }
        if(params.containsKey("period")) {
            course.setPeriod(MapUtil.getInt(params,"period"));
        }
        if(params.containsKey("price")) {
            course.setPrice(BigDecimal.valueOf(MapUtil.getDouble(params,"price")));
        }
        if(params.containsKey("descreption")) {
            course.setDescreption(MapUtil.getStr(params,"descreption"));
        }
        courseService.updateById(course);
        return ResultVoUtil.success("更新成功");
    }

    /**
     * 根据教师ID获取课程列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(courseService.selectPage(params));
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
        Course course = courseService.getById(courseId);
        RobotAssert.notNull(course,"找不相应的课程，请确认课程是否存在");
        course.setEnable(0);
        courseService.updateById(course);
        return ResultVoUtil.success("删除成功");
    }
}

