package com.app.timetable.controller;


import cn.hutool.core.bean.BeanUtil;
import com.app.timetable.common.utils.BaseUtils;
import com.app.timetable.common.utils.RobotAssert;
import com.app.timetable.model.dto.TeacherTimetableDTO;
import com.app.timetable.model.entity.TeacherTimetable;
import com.app.timetable.model.enums.TimetableStatus;
import com.app.timetable.service.ITeacherTimetableService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
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
@RequestMapping("/teacherTimetable")
public class TeacherTimetableController {

    @Autowired
    private ITeacherTimetableService teacherTimetableService;

    /**
     * 教师排课
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam Map<String,Object> params) {
        BaseUtils.getInstance().checkParams(params,new String[]{"courseId","teacherId","number","courseTime","startTime","endTime"});
        TeacherTimetable timetable = BeanUtil.mapToBean(params,TeacherTimetable.class,true);
        timetable.setStatus(TimetableStatus.VALID.getCode());
        teacherTimetableService.save(timetable);
        return ResultVoUtil.success("添加成功");
    }

    /**
     * 获取教师课表列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(teacherTimetableService.selectByPage(params));
    }

    /**
     * 获取课表详情
     * @param timetableId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("timetableId") Long timetableId) {
        TeacherTimetableDTO dto = teacherTimetableService.selectDetailById(timetableId);
        return ResultVoUtil.success(dto);
    }

    /**
     * 发布作业
     * @param timetableId
     * @param homework
     * @return
     */
    @PostMapping("/edit")
    public ResultVo edit(@RequestParam("timetableId") Long timetableId,
                         @RequestParam(value = "homework", required = false) String homework) {
        TeacherTimetable teacherTimetable = teacherTimetableService.getById(timetableId);
        RobotAssert.notNull(teacherTimetable,"找不到相应的课表信息，请确保数据存在。");
        TeacherTimetable timetable = new TeacherTimetable();
        timetable.setId(timetableId);
        timetable.setHomework(homework);
        teacherTimetableService.update(timetable);
        return ResultVoUtil.success("更新成功");
    }

    /**
     * 删除排课信息
     * @param timetableId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("timetableId") String timetableId) {
        TeacherTimetable teacherTimetable = teacherTimetableService.getById(timetableId);
        RobotAssert.notNull(teacherTimetable,"找不到相应的课表信息，请确保数据存在。");
        teacherTimetable.setEnable(0);
        teacherTimetableService.updateById(teacherTimetable);
        return ResultVoUtil.success("删除成功");
    }
}


