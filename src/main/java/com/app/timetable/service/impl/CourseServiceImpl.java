package com.app.timetable.service.impl;

import com.app.timetable.common.model.BaseService;
import com.app.timetable.model.dto.CourseDTO;
import com.app.timetable.model.entity.Course;
import com.app.timetable.mapper.CourseMapper;
import com.app.timetable.service.ICourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class CourseServiceImpl extends BaseService<CourseMapper, Course> implements ICourseService {

    @Override
    public IPage<CourseDTO> selectPage(Map<String,Object> params) {
        Page<CourseDTO> page = initPage(params);
        return baseMapper.selectByPage(page,params);
    }

    @Override
    public CourseDTO selectDetailById(String courseId) {
        return baseMapper.selectDetailById(courseId);
    }
}
