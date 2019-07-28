package com.app.timetable.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.mapper.StudentPurchasedCourseMapper;
import com.app.timetable.mapper.SysUserMapper;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.CookieUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper  userMapper;

    @Autowired
    private StudentPurchasedCourseMapper purchasedCourseMapper;

    @Override
    public void register(SysUser sysUser, HttpServletResponse response) {
        if(StringUtils.isNotBlank(sysUser.getId())) {
            userMapper.updateById(sysUser);
        } else {
            sysUser.setId(ClassObjectUtils.getUUID());
            userMapper.insert(sysUser);
        }
        String token = CookieUtil.getUUToken();
        CookieUtil.setTokenCookie(CommonContent.TOKEN_KEY,token, (int) CommonContent.LOGIN_EXPIRE_TIME, response);
    }

    @Override
    public IPage<SysUserDTO> selectPage(int pageNum, int pageSize, SysUser user) {
        Page<SysUserDTO> page = new Page<>(pageNum,pageSize);
        return userMapper.selectPage(page,user);
    }

    @Override
    public JSONObject studetnDetail(String userId) {
        List<PurchasedCourseDTO> list = purchasedCourseMapper.selectList(userId);
        String teachers = "";
        Integer truancyNum = 0; // 旷课次数
        Integer leaveNum = 0; // 请假次数
        for(PurchasedCourseDTO dto : list) {
            if(StringUtils.isNotBlank(teachers)) {
                teachers += ", ";
            }
            teachers += dto.getTeacherName();
            truancyNum += dto.getTruancyNum()!=null?dto.getTruancyNum():0;
            leaveNum += dto.getLeaveNum()!=null?dto.getLeaveNum():0;
        }
        JSONObject result = new JSONObject();
        result.put("teachers",teachers);
        result.put("truancyNum",truancyNum);
        result.put("leaveNum",leaveNum);
        return result;
    }

    @Override
    public SysUser selectByOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }

    @Override
    public IPage<SysUserDTO> selectMyStudents(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse) {
        Page<SysUserDTO> page = new Page<>(pageNum,pageSize);
        return userMapper.selectMyStudents(page, purchasedCourse);
    }

    @Override
    public List<SysUserDTO> teacherOptions(String tagId) {
        return userMapper.teacherOptions(tagId);
    }
}
