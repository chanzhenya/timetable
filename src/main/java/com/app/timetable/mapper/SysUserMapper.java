package com.app.timetable.mapper;

import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUserDTO> selectByPage(Page<SysUserDTO> page, @Param("params") Map<String,Object> params);

    IPage<SysUserDTO> selectMyStudents(Page<SysUserDTO> page, @Param("purchasedCourse") StudentPurchasedCourse purchasedCourse);

    List<SysUserDTO> teacherOptions(@Param("tagId") String tagId);
}
