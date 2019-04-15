package com.app.timetable.service.impl;

import com.app.timetable.dto.TeacherTimetableDTO;
import com.app.timetable.entity.TeacherTimetable;
import com.app.timetable.mapper.TeacherTimetableMapper;
import com.app.timetable.service.ITeacherTimetableService;
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
public class TeacherTimetableServiceImpl extends ServiceImpl<TeacherTimetableMapper, TeacherTimetable> implements ITeacherTimetableService {

    @Autowired
    private TeacherTimetableMapper timetableMapper;

    @Override
    public IPage<TeacherTimetableDTO> selectByPage(int pageNum, int pageSize, TeacherTimetable timetable) throws Exception {
        Page<TeacherTimetableDTO> page = new Page<>(pageNum, pageSize);
        return timetableMapper.selectByPage(page,timetable);
    }

    @Override
    public TeacherTimetableDTO selectDetailById(String id) throws Exception {
        return timetableMapper.selectDetailById(id);
    }
}
