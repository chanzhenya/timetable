package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ISysUserService;
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
    public ResultVo detail(@RequestParam("studentId") Long studentId) {
        JSONObject result = new JSONObject();
        SysUser sysUser = userService.getById(studentId);
        Map<String,Object> _params = new HashMap<>();
        _params.put("studentId",studentId);
        List<PurchasedCourseDTO> purchasedCourses = purchasedCourseService.selectByPage(_params).getRecords();
        result.put("purchasedCourses",purchasedCourses);
        result.put("student", sysUser);
        return ResultVoUtil.success(result);
    }

}
