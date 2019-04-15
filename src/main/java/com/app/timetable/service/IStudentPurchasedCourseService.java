package com.app.timetable.service;

import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface IStudentPurchasedCourseService extends IService<StudentPurchasedCourse> {

    IPage<PurchasedCourseDTO> selectByPage(int pageNum, int pageSize, String studentId) throws Exception;
}
