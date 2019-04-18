package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.dto.TeacherEvaluationDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.TeacherEvaluation;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherEvaluationService;
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
 * @date 2019/4/18 16:04
 * @description
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ITeacherEvaluationService evaluationService;

    /**
     * 教师信息详情
     * @param teacherId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("teacherId") String teacherId) {
        try {
            JSONObject result = new JSONObject();
            SysUser sysUser = userService.getById(teacherId);
            Course queryCourse = new Course();
            queryCourse.setTeacherId(sysUser.getId());
            IPage<Course> courseIPage = courseService.selectPage(1,20,queryCourse);
            List<Course> courseList = courseIPage.getRecords();

            TeacherEvaluation evaluation = new TeacherEvaluation();
            evaluation.setTeacherId(sysUser.getId());
            IPage<TeacherEvaluationDTO> evaluationDTOIPage = evaluationService.selectByPage(1,10,evaluation);
            List<TeacherEvaluationDTO> evaluationDTOS = evaluationDTOIPage.getRecords();
            result.put("courses",courseList);
            result.put("evaluations",evaluationDTOS);
            result.put("teacher", sysUser);
            return ResultVoUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}
