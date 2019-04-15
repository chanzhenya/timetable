package com.app.timetable.service;

import com.app.timetable.entity.Course;
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
public interface ICourseService extends IService<Course> {

    IPage<Course> selectPage(int pageNum, int pageSize, String teacherId) throws Exception;
}
