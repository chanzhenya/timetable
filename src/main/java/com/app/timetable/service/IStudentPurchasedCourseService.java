package com.app.timetable.service;

import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.enums.TimetableStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface IStudentPurchasedCourseService extends IService<StudentPurchasedCourse> {

    IPage<PurchasedCourseDTO> selectByPage(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse);

    int leaveAndTruancy(String studentId, TimetableStatus timetableStatus);

    List<StudentPurchasedCourse> query(StudentPurchasedCourse purchasedCourse);

    void updateWithSchedule();

    /**
     * 新增已购课程，若新增的已购课程已存在，则在原有的数据上增加剩余课时，及修改截至时间
     * @param purchasedCourse
     */
    void insert(StudentPurchasedCourse purchasedCourse);
}
