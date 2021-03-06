package com.app.timetable.mapper;

import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface StudentPurchasedCourseMapper extends BaseMapper<StudentPurchasedCourse> {

    IPage<PurchasedCourseDTO> selectByPage(Page<PurchasedCourseDTO> page, @Param("params") Map<String,Object> params);

    List<StudentPurchasedCourse> query(@Param("purchasedCourse") StudentPurchasedCourse purchasedCourse);

    void updateBySchedule();
}
