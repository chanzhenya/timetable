package com.app.timetable.service;

import com.app.timetable.model.dto.TeacherTimetableDTO;
import com.app.timetable.model.entity.TeacherTimetable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ITeacherTimetableService extends IService<TeacherTimetable> {

    IPage<TeacherTimetableDTO> selectByPage(int pageNum, int pageSize, TeacherTimetable timetable, Long tagId);

    TeacherTimetableDTO selectDetailById(String id);

    void update(TeacherTimetable timetable);
}
