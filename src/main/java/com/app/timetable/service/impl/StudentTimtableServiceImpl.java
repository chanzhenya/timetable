package com.app.timetable.service.impl;

import com.app.timetable.model.dto.StudentTimetableDTO;
import com.app.timetable.entity.*;
import com.app.timetable.model.entity.*;
import com.app.timetable.model.enums.CourseType;
import com.app.timetable.model.enums.TimetableStatus;
import com.app.timetable.mapper.StudentTimtableMapper;
import com.app.timetable.mapper.TeacherTimetableMapper;
import com.app.timetable.service.IAuditionLogService;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.IStudentTimtableService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ClassObjectUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public  List<StudentTimetableDTO> add(List<String> teacherTimetableIds, Long studentId) {
        //获取预约课的详情
        List<TeacherTimetable> teacherTimetables = teacherTimetableMapper.selectBatchIds(teacherTimetableIds);
        List<Long> courseIds = new ArrayList<>();
        for(TeacherTimetable teacherTimetable:teacherTimetables) {
            courseIds.add(teacherTimetable.getCourseId());
        }
        //查询该学生的试听记录
        QueryWrapper<AuditionLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.in("course_id", courseIds);
        List<AuditionLog> auditionLogs = auditionLogService.list(queryWrapper);

        //获取学生已购买课程
        StudentPurchasedCourse studentPurchasedCourse = new StudentPurchasedCourse();
        studentPurchasedCourse.setStudentId(studentId);
        List<StudentPurchasedCourse> purchasedCourses = studentPurchasedCourseService.query(studentPurchasedCourse);

        List<StudentTimtable> newStudentTimtables = new ArrayList<>();
        List<AuditionLog> auditionLogList = new ArrayList<>();
        List<StudentPurchasedCourse> purchasedCourseList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
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
                studentTimtable.setStudentId(studentId);
                studentTimtable.setStatus(TimetableStatus.VALID.getCode());
                studentTimtable.setCourseId(teacherTimetable.getCourseId());
                studentTimtable.setTeacherId(teacherTimetable.getTeacherId());
                studentTimtable.setCourseTime(teacherTimetable.getCourseTime());
                if(flag1) {
                    studentTimtable.setCourseType(CourseType.AUDITION.getCode());
                } else {
                    studentTimtable.setCourseType(CourseType.FORMAL.getCode());
                }
                newStudentTimtables.add(studentTimtable);
                ids.add(studentTimtable.getId());
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
        if(!purchasedCourseList.isEmpty()) {
            studentPurchasedCourseService.updateBatchById(purchasedCourseList);
        }
        return studentTimtableMapper.selectByIds(ids);
    }

    @Override
    public IPage<StudentTimetableDTO> selectByPage(int pageNum, int pageSize, StudentTimtable timtable) {
        Page<StudentTimetableDTO> page = new Page<>(pageNum,pageSize);
        return studentTimtableMapper.selectDetailList(page,timtable);
    }

    @Override
    public void signIn(StudentTimtable st, String teacherTimetableId) {
        StudentTimtable studentTimtable = studentTimtableMapper.selectById(st.getId());
        studentTimtable.setStatus(st.getStatus());
        studentTimtableMapper.updateById(studentTimtable);

        SysUser student = sysUserService.getById(studentTimtable.getStudentId());
        SysUser teacher = sysUserService.getById(studentTimtable.getTeacherId());

        //学生旷课，登记学生旷课次数
        if(TimetableStatus.TRUANCY.getCode().equals(studentTimtable.getStatus())) {
            StudentPurchasedCourse res = new StudentPurchasedCourse();
            res.setStudentId(studentTimtable.getStudentId());
            res.setCourseId(studentTimtable.getCourseId());
            List<StudentPurchasedCourse> list = studentPurchasedCourseService.query(res);
            StudentPurchasedCourse purchasedCourse = list.get(0);
            purchasedCourse.setTruancyNum(purchasedCourse.getTruancyNum()+1);
            studentPurchasedCourseService.updateById(purchasedCourse);

            student.setTruancy(student.getTruancy()+1);
            teacher.setTruancy(teacher.getTruancy()+1);
        } else if(TimetableStatus.INVALID.getCode().equals(studentTimtable.getStatus())) {
            student.setPeriod(student.getPeriod()+1);
            teacher.setPeriod(teacher.getPeriod()+1);
        }
        sysUserService.updateBatchById(Arrays.asList(student,teacher));

        //更新教师课表，教师已上课
        TeacherTimetable teacherTimetable = teacherTimetableMapper.selectById(teacherTimetableId);
        if(TimetableStatus.VALID.getCode().equals(teacherTimetable.getStatus())) {
            teacherTimetable.setStatus(TimetableStatus.INVALID.getCode());
            teacherTimetableMapper.updateById(teacherTimetable);
        }
    }

    @Override
    public void publishHomework(String id, String homework) {
        StudentTimtable studentTimtable = studentTimtableMapper.selectById(id);
        studentTimtable.setHomework(homework);
        studentTimtableMapper.updateById(studentTimtable);
    }

    @Override
    public int leave(StudentTimtable studentTimtable) {
        StudentTimtable timtable = studentTimtableMapper.selectById(studentTimtable.getId());
        timtable.setStatus(studentTimtable.getStatus());
        studentTimtableMapper.updateById(timtable);
        //获取该已购买课程信息
        StudentPurchasedCourse res = new StudentPurchasedCourse();
        res.setStudentId(timtable.getStudentId());
        res.setCourseId(timtable.getCourseId());
        List<StudentPurchasedCourse> list = studentPurchasedCourseService.query(res);

        //取消试听课程
        if(list.isEmpty() && CourseType.AUDITION.getCode().equals(timtable.getCourseType())) {
            return 0;
        }

        StudentPurchasedCourse purchasedCourse = list.get(0);
        int leaveNum = purchasedCourse.getLeaveNum();
        int remainNum = purchasedCourse.getRemainNum();
        int remain = purchasedCourse.getRemain();

        leaveNum++;
        remainNum--;
        remain++;
        purchasedCourse.setLeaveNum(leaveNum);
        purchasedCourse.setRemainNum(remainNum);
        purchasedCourse.setRemain(remain);
        studentPurchasedCourseService.updateById(purchasedCourse);
        return remainNum;
    }
}
