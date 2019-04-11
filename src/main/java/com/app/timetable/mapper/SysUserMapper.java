package com.app.timetable.mapper;

import com.app.timetable.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    public IPage<SysUser> selectPage(Page<SysUser> page, @Param("user") SysUser user);
}
