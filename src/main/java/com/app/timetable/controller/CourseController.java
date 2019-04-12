package com.app.timetable.controller;


import com.app.timetable.entity.Course;
import com.app.timetable.service.ICourseService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    @PostMapping("/add")
    public ResultVo add(@RequestParam("courseName") String courseName, @RequestParam("tagId") String tagId) {
        try {
            Course course = new Course();
            course.setId(ClassObjectUtils.getUUID());
            course.setName(courseName);
            course.setTagId(tagId);
            course.setStatus(0);
            courseService.save(course);
            return ResultVoUtil.success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResultVo edit(@RequestParam("price")BigDecimal price, @RequestParam("period") Integer period,
                         @RequestParam("descreption") String descreption, @RequestParam("teacherId") String teacherId,
                         @RequestParam("id") String id) {
        try {
            Course course = new Course();
            course.setId(id);
            course.setPeriod(period);
            course.setPrice(price);
            course.setDescreption(descreption);
            course.setTeacherId(teacherId);
            courseService.updateById(course);
            return ResultVoUtil.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}

