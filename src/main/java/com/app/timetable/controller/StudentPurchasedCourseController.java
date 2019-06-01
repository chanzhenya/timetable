package com.app.timetable.controller;


import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.enums.PurchasedCourseStatus;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ITagService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
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
     * @param dueTime 截止日期
     * @param teacherId 教师id
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagId") String tagId, @RequestParam("studentId") String studentId,
                        @RequestParam("remain") Integer remain, @RequestParam("dueTime") long dueTime,
                        @RequestParam("teacerId") String teacherId) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",tagId);
        queryWrapper.eq("teacher_id",teacherId);
        Course course = courseService.getOne(queryWrapper);
        StudentPurchasedCourse purchasedCourse = new StudentPurchasedCourse();
        purchasedCourse.setId(ClassObjectUtils.getUUID());
        purchasedCourse.setCourseId(course.getId());
        purchasedCourse.setStudentId(studentId);
        purchasedCourse.setTeacherId(teacherId);
        purchasedCourse.setRemain(remain);
        purchasedCourse.setStatus(PurchasedCourseStatus.VALID.getCode());
        purchasedCourse.setDueTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(dueTime), ZoneId.of("Asia/Shanghai")));
        purchasedCourse.setCreateTime(LocalDateTime.now());
        purchasedCourseService.insert(purchasedCourse);
        return ResultVoUtil.success("新增成功");
    }


    /**
     * 根据学生ID获取已购买的课程列表
     * @param pageNum 页码
     * @param pageSize 行数
     * @param studentId 学生id
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize,
                         @RequestParam(value = "studentId", required = false) String studentId) {
        StudentPurchasedCourse purchasedCourse = new StudentPurchasedCourse();
        purchasedCourse.setStudentId(studentId);
        IPage<PurchasedCourseDTO> dtoiPage = purchasedCourseService.selectByPage(pageNum,pageSize,purchasedCourse);
        return ResultVoUtil.success(dtoiPage);
    }

    /**
     * 删除已购课程
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("id") String id) {
        purchasedCourseService.removeById(id);
        return ResultVoUtil.success("删除成功");
    }
}

