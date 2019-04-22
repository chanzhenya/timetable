package com.app.timetable.service.impl;

import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.AuditionLog;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.entity.StudentTimtable;
import com.app.timetable.entity.TeacherTimetable;
import com.app.timetable.enums.CourseType;
import com.app.timetable.enums.TimetableStatus;
import com.app.timetable.mapper.StudentPurchasedCourseMapper;
import com.app.timetable.mapper.StudentTimtableMapper;
import com.app.timetable.mapper.TeacherTimetableMapper;
import com.app.timetable.service.IAuditionLogService;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.IStudentTimtableService;
import com.app.timetable.utils.ClassObjectUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Transactional
@Service
public class StudentTimtableServiceImpl extends ServiceImpl<StudentTimtableMapper, StudentTimtable> implements IStudentTimtableService {

    @Autowired
    private StudentTimtableMapper studentTimtableMapper;

    @Autowired
    private IAuditionLogService auditionLogService;

    @Autowired
    private TeacherTimetableMapper teacherTimetableMapper;

    @Autowired
    private IStudentPurchasedCourseService studentPurchasedCourseService;

    @Override
    public  List<StudentTimtable> add(String[] teacherTimetableIds, String studentId) throws Exception {
        //获取预约课的详情
        List<TeacherTimetable> teacherTimetables = teacherTimetableMapper.selectBatchIds(Arrays.asList(teacherTimetableIds));
        List<String> courseIds = new ArrayList<>();
        for(TeacherTimetable teacherTimetable:teacherTimetables) {
            courseIds.add(teacherTimetable.getCourseId());
        }
        //查询该学生的试听记录
        QueryWrapper<AuditionLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.in("course_id", courseIds);
        List<AuditionLog> auditionLogs = auditionLogService.list(queryWrapper);

        //获取学生已购买课程
        QueryWrapper<StudentPurchasedCourse> purchasedCourseQueryWrapper = new QueryWrapper<>();
        purchasedCourseQueryWrapper.eq("student_id",studentId);
        List<StudentPurchasedCourse> purchasedCourses = studentPurchasedCourseService.list(purchasedCourseQueryWrapper);

        List<StudentTimtable> newStudentTimtables = new ArrayList<>();
        List<AuditionLog> auditionLogList = new ArrayList<>();
        List<StudentPurchasedCourse> purchasedCourseList = new ArrayList<>();
        for(TeacherTimetable teacherTimetable:teacherTimetables) {
            boolean flag1 = true; // 是否可以试听
            boolean flag2 = false; // 是否可以预约课程
            //判断该课程是否可试听
            for(AuditionLog log:auditionLogs) {
                if(log.getCourseId().equals(teacherTimetable.getCourseId())) { //该课程已试听过，不可再试听
                    flag1 = false;
                    break;
                }
            }

            for(StudentPurchasedCourse purchasedCourse:purchasedCourses) {
                if(purchasedCourse.getCourseId().equals(teacherTimetable.getCourseId())) { //正常预约课程，已购买该课程
                    flag2 = true;
                    int remain = purchasedCourse.getRemain(); // 剩余课时
                    remain --; // 预约课程，减课时
                    purchasedCourse.setRemain(remain);
                    if(flag1) { // 已购买，只使用课时听课，不试听
                        flag1 = false;
                    }
                    purchasedCourseList.add(purchasedCourse); // 更新剩余课时
                    break;
                }
            }

            if(!flag2) { // 若flag1=true该课程可以试听，但是没有购买，则可以添加课表信息；flag1=false课程已试听过且未购买课程，不可添加课表信息
                flag2 = flag1;
            }

            //可预约课程，并添加课表信息
            if(flag2) {
                StudentTimtable studentTimtable = new StudentTimtable();
                studentTimtable.setId(ClassObjectUtils.getUUID());
                studentTimtable.setStudentId(studentId);
                studentTimtable.setCourseId(teacherTimetable.getCourseId());
                studentTimtable.setTeacherId(teacherTimetable.getTeacherId());
                studentTimtable.setCourseTime(teacherTimetable.getCourseTime());
                if(flag1) {
                    studentTimtable.setCourseType(CourseType.AUDITION.getCode());
                } else {
                    studentTimtable.setCourseType(CourseType.FORMAL.getCode());
                }
                studentTimtable.setCreateTime(LocalDateTime.now());
                newStudentTimtables.add(studentTimtable);
            }

            if(flag1) { //添加试听记录
                AuditionLog auditionLog = new AuditionLog();
                auditionLog.setId(ClassObjectUtils.getUUID());
                auditionLog.setStudentId(studentId);
                auditionLog.setCourseId(teacherTimetable.getCourseId());
                auditionLog.setTeacherId(teacherTimetable.getTeacherId());
                auditionLogList.add(auditionLog);
            }
        }

        studentTimtableMapper.insertByBatch(newStudentTimtables);
        auditionLogService.saveBatch(auditionLogList);
        studentPurchasedCourseService.updateBatchById(purchasedCourseList);
        return newStudentTimtables;
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
