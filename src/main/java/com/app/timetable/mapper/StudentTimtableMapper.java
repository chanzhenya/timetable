package com.app.timetable.mapper;

import com.app.timetable.model.dto.StudentTimetableDTO;
import com.app.timetable.model.entity.StudentTimtable;
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
public interface StudentTimtableMapper extends BaseMapper<StudentTimtable> {

    IPage<StudentTimetableDTO> selectDetailList(Page<StudentTimetableDTO> page, @Param("timetable") StudentTimtable timtable);

    List<StudentTimetableDTO> selectByCourse(@Param("courseId") String courseId);

    void insertByBatch(List<StudentTimtable> timtableList);

    List<StudentTimetableDTO> selectByIds(List<String> ids);
}
