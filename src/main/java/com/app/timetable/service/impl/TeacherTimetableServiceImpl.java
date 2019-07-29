package com.app.timetable.service.impl;

import com.app.timetable.common.utils.BaseUtils;
import com.app.timetable.model.dto.TeacherTimetableDTO;
import com.app.timetable.model.entity.TeacherTimetable;
import com.app.timetable.mapper.TeacherTimetableMapper;
import com.app.timetable.service.ITeacherTimetableService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherTimetableServiceImpl extends ServiceImpl<TeacherTimetableMapper, TeacherTimetable> implements ITeacherTimetableService {

    @Override
    public IPage<TeacherTimetableDTO> selectByPage(Map<String,Object> params) {
        Page<TeacherTimetableDTO> page = BaseUtils.getInstance().initPage(params);
        return baseMapper.selectByPage(page,params);
    }

    @Override
    public TeacherTimetableDTO selectDetailById(String id) {
        return baseMapper.selectDetailById(id);
    }

    @Override
    public void update(TeacherTimetable timetable) {
        UpdateWrapper<TeacherTimetable> updateWrapper = new UpdateWrapper<>();
        if(StringUtils.isNotBlank(timetable.getHomework())) {
            updateWrapper.set("homework",timetable.getHomework());
        }
        if(timetable.getStatus() != null) {
            updateWrapper.set("status",timetable.getStatus());
        }
        updateWrapper.eq("id", timetable.getId());
        update(timetable,updateWrapper);
    }
}
