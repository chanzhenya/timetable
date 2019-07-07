package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.dto.TeacherEvaluationDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.TeacherEvaluation;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Judith
 * @date 2019/4/18 16:07
 * @description
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IStudentPurchasedCourseService purchasedCourseService;

    /**
     * 学生信息详情
     * @param studentId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("studentId") String studentId) {
        JSONObject result = new JSONObject();
        SysUser sysUser = userService.getById(studentId);
        StudentPurchasedCourse purchasedCourse = new StudentPurchasedCourse();
        purchasedCourse.setStudentId(sysUser.getId());
        IPage<PurchasedCourseDTO> courseDTOIPage = purchasedCourseService.selectByPage(1,10,purchasedCourse);
        List<PurchasedCourseDTO> purchasedCourses = courseDTOIPage.getRecords();
        result.put("purchasedCourses",purchasedCourses);
        result.put("student", sysUser);
        return ResultVoUtil.success(result);
    }

}
