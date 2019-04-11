package com.app.timetable.service;

import com.app.timetable.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ISysUserService extends IService<SysUser> {

    void register(SysUser sysUser, HttpServletResponse response) throws Exception;

    IPage<SysUser> selectPage(int pageNum, int pageSize, SysUser user) throws Exception;

    SysUser getUserDetail(String userId) throws Exception;
}
