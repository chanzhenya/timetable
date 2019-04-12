package com.app.timetable.mapper;

import com.app.timetable.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface StudentMapper extends BaseMapper<Student> {

    Student selectByUserId(String userId);
}
