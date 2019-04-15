package com.app.timetable.service.impl;

import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.enums.PurchasedCourseStatus;
import com.app.timetable.mapper.StudentPurchasedCourseMapper;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

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

    @Override
    public IPage<PurchasedCourseDTO> selectByPage(int pageNum, int pageSize, String studentId) throws Exception {
        Page<PurchasedCourseDTO> purchasedCourseDTOPage = new Page<>(pageNum,pageSize);
        IPage<PurchasedCourseDTO> dtoiPage = purchasedCourseMapper.selectByPage(purchasedCourseDTOPage,studentId);
        //计算已购买的课程距离截止日期还有多少天
        for(PurchasedCourseDTO dto : dtoiPage.getRecords()) {
            if(PurchasedCourseStatus.VALID.getCode().equals(dto.getStatus())) {
                Duration duration = Duration.between(LocalDateTime.now(),dto.getDueTime());
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
}
