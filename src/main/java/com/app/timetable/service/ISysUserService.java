package com.app.timetable.service;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUserDTO> selecBytPage(Map<String,Object> params);

    JSONObject studetnDetail(String userId);



    IPage<SysUserDTO> selectMyStudents(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse);

    List<SysUserDTO> teacherOptions(String tagId);


    SysUser saveSysUser(String openId);
    SysUser getByOpenId(String openId);
}
