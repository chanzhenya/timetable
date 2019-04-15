package com.app.timetable.mapper;

import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.StudentTimtable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentTimtableMapper extends BaseMapper<StudentTimtable> {

    List<StudentTimetableDTO> selectDetailByCourse(@Param("courseId") String courseId);
}
