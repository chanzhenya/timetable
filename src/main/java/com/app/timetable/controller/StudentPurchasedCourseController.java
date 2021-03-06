package com.app.timetable.controller;


import cn.hutool.core.date.DateUtil;
import com.app.timetable.common.utils.RobotAssert;
import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.entity.Course;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.enums.PurchasedCourseStatus;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@RequestMapping("/purchasedCourse")
public class StudentPurchasedCourseController {

    @Autowired
    private IStudentPurchasedCourseService purchasedCourseService;

    @Autowired
    private ICourseService courseService;

    /**
     * 为学生新增已购买的课程信息
     * @param tagId 课程分类id
     * @param studentId 学生id
     * @param remain 剩余课时
     * @param dueTime 截止日期 yyyy-MM-dd
     * @param teacherId 教师id
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagId") Long tagId, @RequestParam("studentId") Long studentId,
                        @RequestParam("remain") Integer remain, @RequestParam("dueTime") String dueTime,
                        @RequestParam("teacerId") Long teacherId) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",tagId);
        queryWrapper.eq("teacher_id",teacherId);
        queryWrapper.eq("enable",1);
        Course course = courseService.getOne(queryWrapper);
        RobotAssert.notNull(course,"找不到相应的课程，请确认课程是否被删除。");

        StudentPurchasedCourse purchasedCourse = new StudentPurchasedCourse();
        purchasedCourse.setCourseId(course.getId());
        purchasedCourse.setStudentId(studentId);
        purchasedCourse.setTeacherId(teacherId);
        purchasedCourse.setRemain(remain);
        purchasedCourse.setStatus(PurchasedCourseStatus.VALID.getCode());
        purchasedCourse.setDueTime(DateUtil.parse(dueTime,"yyyy-MM-dd"));
        purchasedCourseService.insert(purchasedCourse);
        return ResultVoUtil.success("新增成功");
    }


    /**
     * 根据学生ID获取已购买的课程列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(purchasedCourseService.selectByPage(params));
    }

    /**
     * 删除已购课程
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("id") String id) {
        StudentPurchasedCourse purchasedCourse = purchasedCourseService.getById(id);
        RobotAssert.notNull(purchasedCourse,"找不到相应的已购买课程，请确认数据是否存在");
        purchasedCourse.setEnable(0);
        purchasedCourseService.updateById(purchasedCourse);
        return ResultVoUtil.success("删除成功");
    }
}

