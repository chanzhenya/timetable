package com.app.timetable.service.impl;

import com.app.timetable.entity.Course;
import com.app.timetable.enums.CourseStatus;
import com.app.timetable.mapper.CourseMapper;
import com.app.timetable.service.ICourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Course> selectPage(int pageNum, int pageSize, Course course) throws Exception {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(course.getTeacherId())) {
            queryWrapper.eq("teacher_id", course.getTeacherId());
        }
        if(course.getStatus() != null) {
            queryWrapper.ne("status", course.getStatus());
        }
        queryWrapper.orderByDesc("create_time");
        Page<Course> page = new Page<>(pageNum,pageSize);
        return courseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Course selectDetailById(String courseId) throws Exception {
        return courseMapper.selectById(courseId);
    }
}
