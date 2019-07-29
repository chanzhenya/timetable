package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.CourseDTO;
import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.dto.TeacherEvaluationDTO;
import com.app.timetable.model.entity.Course;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.model.entity.TeacherEvaluation;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherEvaluationService;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        JSONObject result = new JSONObject();
        SysUser sysUser = userService.getById(teacherId);
        Map<String,Object> _params = new HashMap<>();
        _params.put("teacherId",teacherId);
        IPage<CourseDTO> courseIPage = courseService.selectPage(_params);
        List<CourseDTO> courseList = courseIPage.getRecords();

        TeacherEvaluation evaluation = new TeacherEvaluation();
        evaluation.setTeacherId(sysUser.getId());
        IPage<TeacherEvaluationDTO> evaluationDTOIPage = evaluationService.selectByPage(_params);
        List<TeacherEvaluationDTO> evaluationDTOS = evaluationDTOIPage.getRecords();
        result.put("courses",courseList);
        result.put("evaluations",evaluationDTOS);
        result.put("teacher", sysUser);
        return ResultVoUtil.success(result);
    }

    /**
     * 教师名下的学生
     * @param teacherId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/myStudents")
    public ResultVo myStudents(@RequestParam("teacherId") Long teacherId,
                               @RequestParam(value = "courseId", required = false) Long courseId,
                               @RequestParam(value = "pageNum", required = false,defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize) {
        StudentPurchasedCourse purchasedCourse = new StudentPurchasedCourse();
        purchasedCourse.setTeacherId(teacherId);
        purchasedCourse.setCourseId(courseId);
        IPage<SysUserDTO> sysUserDTOIPage = userService.selectMyStudents(pageNum,pageSize,purchasedCourse);
        return ResultVoUtil.success(sysUserDTOIPage);
    }

    /**
     * 教师选项
     * @param tagId
     * @return
     */
    @PostMapping("/options")
    public ResultVo options(@RequestParam("tagId") String tagId) {
        List<SysUserDTO> options = userService.teacherOptions(tagId);
        return ResultVoUtil.success(options);
    }
}
