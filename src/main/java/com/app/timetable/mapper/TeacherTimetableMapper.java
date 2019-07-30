package com.app.timetable.mapper;

import com.app.timetable.model.dto.TeacherTimetableDTO;
import com.app.timetable.model.entity.TeacherTimetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface TeacherTimetableMapper extends BaseMapper<TeacherTimetable> {

    IPage<TeacherTimetableDTO> selectByPage(Page<TeacherTimetableDTO> page, @Param("params") Map<String,Object> params);

    TeacherTimetableDTO selectDetailById(@Param("id") Long id);
}
