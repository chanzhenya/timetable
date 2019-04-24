package com.app.timetable.service;

import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.enums.PurchasedCourseStatus;
import com.app.timetable.enums.TimetableStatus;
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

    IPage<PurchasedCourseDTO> selectByPage(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse) throws Exception;

    int leaveAndTruancy(String studentId, TimetableStatus timetableStatus) throws Exception;

    List<StudentPurchasedCourse> query(StudentPurchasedCourse purchasedCourse) throws Exception;
}
