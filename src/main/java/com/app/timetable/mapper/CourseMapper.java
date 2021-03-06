package com.app.timetable.mapper;

import com.app.timetable.model.dto.CourseDTO;
import com.app.timetable.model.entity.Course;
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
public interface CourseMapper extends BaseMapper<Course> {

    CourseDTO selectDetailById(@Param("courseId") String courseId);

    IPage<CourseDTO> selectByPage(Page<CourseDTO> page, @Param("params") Map<String,Object> params);
}
