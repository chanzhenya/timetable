package com.app.timetable.service.impl;

import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.AuditionLog;
import com.app.timetable.entity.StudentTimtable;
import com.app.timetable.enums.CourseType;
import com.app.timetable.mapper.StudentTimtableMapper;
import com.app.timetable.service.IAuditionLogService;
import com.app.timetable.service.IStudentTimtableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class StudentTimtableServiceImpl extends ServiceImpl<StudentTimtableMapper, StudentTimtable> implements IStudentTimtableService {

    @Autowired
    private StudentTimtableMapper studentTimtableMapper;

    @Autowired
    private IAuditionLogService auditionLogService;

    @Override
    public void add(List<StudentTimtable> timtableList) throws Exception {
        for(StudentTimtable timtable:timtableList) {
            QueryWrapper<AuditionLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("teacher_id", timtable.getTeacherId());
            queryWrapper.eq("course_id", timtable.getCourseId());
            queryWrapper.eq("student_id", timtable.getStudentId());
            List<AuditionLog> auditionLogs = auditionLogService.list(queryWrapper);
            if (auditionLogs.isEmpty()) {
                timtable.setCourseType(CourseType.AUDITION.getCode());
            } else {
                timtable.setCourseType(CourseType.FORMAL.getCode());
            }
        }
        studentTimtableMapper.insertByBatch(timtableList);
    }

    @Override
    public IPage<StudentTimetableDTO> selectBuPage(int pageNum, int pageSize, StudentTimtable timtable) {
        Page<StudentTimetableDTO> page = new Page<>(pageNum,pageSize);
        return studentTimtableMapper.selectDetailList(page,timtable);
    }
}
