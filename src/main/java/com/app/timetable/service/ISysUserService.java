package com.app.timetable.service;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.SysUserDTO;
import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ISysUserService extends IService<SysUser> {

    void register(SysUser sysUser, HttpServletResponse response);

    IPage<SysUserDTO> selectPage(int pageNum, int pageSize, SysUser user);

    JSONObject studetnDetail(String userId);

    SysUser selectByOpenId(String openId);

    IPage<SysUserDTO> selectMyStudents(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse);

    List<SysUserDTO> teacherOptions(String tagId);
}
