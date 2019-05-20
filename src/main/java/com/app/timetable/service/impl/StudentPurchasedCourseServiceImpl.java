package com.app.timetable.service.impl;

import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.entity.SysConfig;
import com.app.timetable.enums.PurchasedCourseStatus;
import com.app.timetable.enums.TimetableStatus;
import com.app.timetable.mapper.StudentPurchasedCourseMapper;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ISysConfigService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class StudentPurchasedCourseServiceImpl extends ServiceImpl<StudentPurchasedCourseMapper, StudentPurchasedCourse> implements IStudentPurchasedCourseService {

    @Autowired
    private StudentPurchasedCourseMapper purchasedCourseMapper;

    @Autowired
    private ISysConfigService configService;

    @Override
    public IPage<PurchasedCourseDTO> selectByPage(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse) {
        Page<PurchasedCourseDTO> purchasedCourseDTOPage = new Page<>(pageNum,pageSize);
        IPage<PurchasedCourseDTO> dtoiPage = purchasedCourseMapper.selectByPage(purchasedCourseDTOPage,purchasedCourse);
        //计算已购买的课程距离截止日期还有多少天
        for(PurchasedCourseDTO dto : dtoiPage.getRecords()) {
            if(PurchasedCourseStatus.VALID.getCode().equals(dto.getStatus())) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dueTime = LocalDateTime.parse(dto.getDueTime(),df);
                Duration duration = Duration.between(LocalDateTime.now(),dueTime);
                long days = duration.toDays();
                if(days <= 0) {
                    dto.setStatus(PurchasedCourseStatus.INVALID.getCode());
                }
                days = Math.max(0,days);
                dto.setCountDownDays(days);
            }
        }
        return dtoiPage;
    }

    @Override
    public int leaveAndTruancy(String studentId, TimetableStatus timetableStatus) {
        StudentPurchasedCourse purchasedCourse = purchasedCourseMapper.selectById(studentId);
        List<SysConfig> sysConfigs = configService.list();
        SysConfig sysConfig = sysConfigs.get(0);
        int res = 0;
        if(TimetableStatus.LEAVE.equals(timetableStatus)) {
            int leaveNum = purchasedCourse.getLeaveNum();
            leaveNum += 1;
            purchasedCourse.setLeaveNum(leaveNum);
            res = Math.max(sysConfig.getNumber()-leaveNum,0);
        } else if(TimetableStatus.TRUANCY.equals(timetableStatus)) {
            int truancyNum = purchasedCourse.getTruancyNum();
            truancyNum+=1;
            purchasedCourse.setTruancyNum(truancyNum);
        }
        purchasedCourseMapper.updateById(purchasedCourse);
        return res;
    }

    @Override
    public List<StudentPurchasedCourse> query(StudentPurchasedCourse purchasedCourse) {
        return purchasedCourseMapper.query(purchasedCourse);
    }

    @Override
    public void updateWithSchedule() {
        purchasedCourseMapper.updateBySchedule();
    }

    @Override
    public void insert(StudentPurchasedCourse purchasedCourse) {
        SysConfig sysConfig = configService.getConfig();
        //初始化请假/旷课次数
        purchasedCourse.setLeaveNum(0);
        purchasedCourse.setTruancyNum(0);
        purchasedCourse.setRemainNum(sysConfig.getNumber());
        purchasedCourseMapper.insert(purchasedCourse);
    }
}
