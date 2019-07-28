package com.app.timetable.mapper;

import com.app.timetable.model.dto.TeacherTimetableDTO;
import com.app.timetable.model.entity.TeacherTimetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface TeacherTimetableMapper extends BaseMapper<TeacherTimetable> {

    IPage<TeacherTimetableDTO> selectByPage(Page<TeacherTimetableDTO> page, @Param("timetable") TeacherTimetable timetable, @Param("tagId") Long tagId);

    TeacherTimetableDTO selectDetailById(@Param("id") String id);
}
