package com.app.timetable.service;

import com.app.timetable.model.dto.StudentTimetableDTO;
import com.app.timetable.model.entity.StudentTimtable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface IStudentTimetableService extends IService<StudentTimtable> {

    /**
     * 预约课程
     * @param teacherTimetableIds
     * @throws Exception
     */
    List<StudentTimetableDTO> add(List<Long> teacherTimetableIds, Long studentId);

    IPage<StudentTimetableDTO> selectByPage(Map<String,Object> params);

    /**
     * 签到及旷课登记
     * @param studentTimtable
     * @param teacherTimetableId
     */
    void signIn(StudentTimtable studentTimtable, String teacherTimetableId);

    /**
     * 发布学生个人作业
     * @param id
     * @param homework
     */
    void publishHomework(String id, String homework);

    /**
     * 学生请假处理
     * @param studentTimtable
     * @return
     * @throws Exception
     */
    int leave(StudentTimtable studentTimtable);
}
