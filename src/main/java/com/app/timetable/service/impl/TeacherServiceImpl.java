package com.app.timetable.service.impl;

import com.app.timetable.dto.TeacherDTO;
import com.app.timetable.entity.Teacher;
import com.app.timetable.mapper.TeacherMapper;
import com.app.timetable.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
