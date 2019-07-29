package com.app.timetable.service;

import com.app.timetable.model.dto.CourseDTO;
import com.app.timetable.model.entity.Course;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ICourseService extends IService<Course> {

    IPage<CourseDTO> selectPage(Map<String,Object> params);

    CourseDTO selectDetailById(String courseId);

}
