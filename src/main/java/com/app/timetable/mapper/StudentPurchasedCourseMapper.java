package com.app.timetable.mapper;

import com.app.timetable.dto.PurchasedCourseDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface StudentPurchasedCourseMapper extends BaseMapper<StudentPurchasedCourse> {

    IPage<PurchasedCourseDTO> selectByPage(Page<PurchasedCourseDTO> page, StudentPurchasedCourse purchasedCourse);

    List<PurchasedCourseDTO> selectList(String StudentId);
}
