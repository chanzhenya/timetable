package com.app.timetable.service.impl;

import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.AuditionLog;
import com.app.timetable.entity.StudentTimtable;
import com.app.timetable.entity.TeacherTimetable;
import com.app.timetable.enums.CourseType;
import com.app.timetable.enums.TimetableStatus;
import com.app.timetable.mapper.StudentTimtableMapper;
import com.app.timetable.mapper.TeacherTimetableMapper;
import com.app.timetable.service.IAuditionLogService;
import com.app.timetable.service.IStudentTimtableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private TeacherTimetableMapper teacherTimetableMapper;

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

    @Override
    public void signIn(StudentTimtable studentTimtable, String teacherTimetableId) throws Exception {
        update(studentTimtable);

        //更新教师课表，教师已上课
        TeacherTimetable teacherTimetable = teacherTimetableMapper.selectById(teacherTimetableId);
        if(TimetableStatus.VALID.getCode().equals(teacherTimetable.getStatus())) {
            teacherTimetable.setStatus(TimetableStatus.INVALID.getCode());
            teacherTimetableMapper.updateById(teacherTimetable);
        }
    }

    @Override
    public void publishHomework(String id, String homework) throws Exception {
        StudentTimtable studentTimtable = new StudentTimtable();
        studentTimtable.setId(id);
        studentTimtable.setHomework(homework);
        update(studentTimtable);
    }

    @Override
    public int leaveAndTruancy(StudentTimtable studentTimtable) throws Exception {
        update(studentTimtable);
        return 0;
    }

    private void update(StudentTimtable studentTimtable) {
        UpdateWrapper<StudentTimtable> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",studentTimtable.getId());
        if(StringUtils.isNotBlank(studentTimtable.getHomework())) {
            updateWrapper.set("homework",studentTimtable.getHomework());
        }
        if(studentTimtable.getStatus() != null) {
            updateWrapper.set("status",studentTimtable.getStatus());
        }
        studentTimtableMapper.update(studentTimtable,updateWrapper);
    }
}
