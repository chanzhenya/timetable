package com.app.timetable.mapper;

import com.app.timetable.model.dto.StudentTimetableDTO;
import com.app.timetable.model.entity.StudentTimtable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface StudentTimetableMapper extends BaseMapper<StudentTimtable> {

    IPage<StudentTimetableDTO> selectDetailList(Page<StudentTimetableDTO> page, @Param("params") Map<String,Object> params);

    List<StudentTimetableDTO> selectByCourse(@Param("courseId") Long courseId);

    void insertByBatch(List<StudentTimtable> timetableList);

    List<StudentTimetableDTO> selectByIds(List<Long> ids);
}
