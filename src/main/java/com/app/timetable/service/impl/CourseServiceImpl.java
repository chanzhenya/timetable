package com.app.timetable.service.impl;

import com.app.timetable.entity.Course;
import com.app.timetable.enums.CourseStatus;
import com.app.timetable.mapper.CourseMapper;
import com.app.timetable.service.ICourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public IPage<Course> selectPage(int pageNum, int pageSize, String teacherId) throws Exception {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        queryWrapper.ne("status",CourseStatus.UNPUBLISHED.getCode());
        Page<Course> page = new Page<>(pageNum,pageSize);
        return courseMapper.selectPage(page,queryWrapper);
    }
}
