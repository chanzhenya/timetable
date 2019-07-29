package com.app.timetable.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.StudentTimetableDTO;
import com.app.timetable.model.entity.StudentTimtable;
import com.app.timetable.model.enums.TimetableStatus;
import com.app.timetable.service.IStudentTimtableService;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/studentTimtable")
public class StudentTimtableController {

    @Autowired
    private IStudentTimtableService studentTimtableService;

    /**
     * 预约课程
     * @param teacherTimetableIds 教师课表id
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("teacherTimetableIds") String teacherTimetableIds, @RequestParam("studentId") Long studentId) {
        JSONArray jsonArray = JSONArray.parseArray(teacherTimetableIds);
        List<Long> idArray = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            idArray.add(obj.getLong("id"));
        }
        List<StudentTimetableDTO> studentTimtables = studentTimtableService.add(idArray, studentId);
        return ResultVoUtil.success(studentTimtables);
    }


    /**
     * 根据学生ID获取课表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(studentTimtableService.selectByPage(params));
    }

    /**
     * 学生签到
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/signIn")
    public ResultVo signIn(@RequestParam("status") Integer status, @RequestParam("id") Long id,
                           @RequestParam("teacherTimetableId") String teacherTimetableId) {
        StudentTimtable timtable = new StudentTimtable();
        timtable.setId(id);
        timtable.setStatus(status);
        studentTimtableService.signIn(timtable,teacherTimetableId);
        return ResultVoUtil.success("操作成功");
    }

    /**
     * 发布学生个人作业
     * @param id
     * @param homework
     * @return
     */
    @PostMapping("/homework")
    public ResultVo homework(@RequestParam("id") String id, @RequestParam("homework") String homework) {
        studentTimtableService.publishHomework(id,homework);
        return ResultVoUtil.success("发布成功");
    }

    /**
     * 请假，取消课程
     * @param id
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo leave(@RequestParam("id") Long id) {
        StudentTimtable timtable = new StudentTimtable();
        timtable.setId(id);
        timtable.setStatus(TimetableStatus.LEAVE.getCode());
        int result = studentTimtableService.leave(timtable);
        return ResultVoUtil.success(result);
    }
}

