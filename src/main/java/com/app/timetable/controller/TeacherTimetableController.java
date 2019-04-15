package com.app.timetable.controller;


import com.app.timetable.dto.TeacherTimetableDTO;
import com.app.timetable.entity.TeacherTimetable;
import com.app.timetable.enums.CourseType;
import com.app.timetable.enums.TimetableStatus;
import com.app.timetable.service.ITeacherTimetableService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/teacherTimetable")
public class TeacherTimetableController {

    @Autowired
    private ITeacherTimetableService teacherTimetableService;

    /**
     * 教师排课
     * @param courseId
     * @param teacherId
     * @param number
     * @param courseTime
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("courseId") String courseId, @RequestParam("teacherId") String teacherId,
                        @RequestParam("number") Integer number, @RequestParam("courseTime") String courseTime) {
        try {
            TeacherTimetable timetable = new TeacherTimetable();
            timetable.setId(ClassObjectUtils.getUUID());
            timetable.setTeacherId(teacherId);
            timetable.setCourseId(courseId);
            timetable.setNumber(number);
            timetable.setCourseTime(LocalDateTime.parse(courseTime));
            timetable.setCreateTime(LocalDateTime.now());
            timetable.setStatus(TimetableStatus.VALID.getCode());
            teacherTimetableService.save(timetable);
            return ResultVoUtil.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 获取教师课表列表
     * @param teacherId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "teacherId", required = false) String teacherId,
                         @RequestParam(value = "courseId", required = false) String courseId,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            TeacherTimetable timetable = new TeacherTimetable();
            timetable.setTeacherId(teacherId);
            timetable.setCourseId(courseId);
            IPage<TeacherTimetableDTO> teacherTimetableIPage = teacherTimetableService.selectByPage(pageNum,pageSize,timetable);
            return ResultVoUtil.success(teacherTimetableIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 获取课表详情
     * @param teacherId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("teacherId") String teacherId) {
        try {
            TeacherTimetableDTO dto = teacherTimetableService.selectDetailById(teacherId);
            return ResultVoUtil.success(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}


