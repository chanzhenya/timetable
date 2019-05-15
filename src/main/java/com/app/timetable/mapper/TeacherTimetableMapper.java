package com.app.timetable.mapper;

import com.app.timetable.dto.TeacherTimetableDTO;
import com.app.timetable.entity.TeacherTimetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface TeacherTimetableMapper extends BaseMapper<TeacherTimetable> {

    IPage<TeacherTimetableDTO> selectByPage(Page<TeacherTimetableDTO> page, @Param("timetable") TeacherTimetable timetable, @Param("tagId") String tagId);

    TeacherTimetableDTO selectDetailById(@Param("id") String id);
}
