package com.app.timetable.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.StudentTimtable;
import com.app.timetable.enums.TimetableStatus;
import com.app.timetable.service.IStudentTimtableService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
     * @param courses
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("courses") String courses) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(courses);
            List<StudentTimtable> timtableList = new ArrayList<>();
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                StudentTimtable timtable = new StudentTimtable();
                timtable.setId(ClassObjectUtils.getUUID());
                timtable.setTeacherId(item.getString("teacherId"));
                timtable.setCourseId(item.getString("courseId"));
                timtable.setStudentId(item.getString("studentId"));
                timtable.setStatus(TimetableStatus.VALID.getCode());
                timtable.setCourseTime(LocalDateTime.parse(item.getString("studentId")));
                timtable.setCreateTime(LocalDateTime.now());

                timtableList.add(timtable);
            }
            studentTimtableService.add(timtableList);
            return ResultVoUtil.success("预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success(e.getMessage());
        }
    }


    /**
     * 根据学生ID获取课表
     * @param studentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "studentId", required = false) String studentId,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            StudentTimtable timtable = new StudentTimtable();
            timtable.setStudentId(studentId);
            IPage<StudentTimetableDTO> dtoiPage = studentTimtableService.selectBuPage(pageNum,pageSize,timtable);
            return ResultVoUtil.success(dtoiPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 学生签到
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/signIn")
    public ResultVo signIn(@RequestParam("status") Integer status, @RequestParam("id") String id,
                           @RequestParam("teacherTimetableId") String teacherTimetableId) {
        try {
            StudentTimtable timtable = new StudentTimtable();
            timtable.setId(id);
            timtable.setStatus(status);
            studentTimtableService.signIn(timtable,teacherTimetableId);
            return ResultVoUtil.success("签到成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 发布学生个人作业
     * @param id
     * @param homework
     * @return
     */
    @PostMapping("/homework")
    public ResultVo homework(@RequestParam("id") String id, @RequestParam("homework") String homework) {
        try {
            studentTimtableService.publishHomework(id,homework);
            return ResultVoUtil.success("发布成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 请假，取消课程
     * @param id
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo leave(@RequestParam("studentId") String studentId,@RequestParam("id") String id) {
        try {
            StudentTimtable timtable = new StudentTimtable();
            timtable.setId(id);
            timtable.setStudentId(studentId);
            timtable.setStatus(TimetableStatus.LEAVE.getCode());
            studentTimtableService.leaveAndTruancy(timtable);
            return ResultVoUtil.success("取消成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}

