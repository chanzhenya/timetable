package com.app.timetable.schedule;

import com.app.timetable.service.IStudentPurchasedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Judith
 * @date 2019/4/25 19:49
 * @description 每个月的1号清空学生的请假次数
 */
@Component
public class TimetableSchedule {

    @Autowired
    private IStudentPurchasedCourseService purchasedCourseService;

    /**
     * 每月1号的0:10:00执行
     */
    @Scheduled(cron = "0 10 0 1 * ?")
    //@Scheduled(cron = "0/5 * * * * ? ")
    public void task() {
        try {
            purchasedCourseService.updateWithSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
