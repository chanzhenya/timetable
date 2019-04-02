package com.app.timetable.service.impl;

import com.app.timetable.entity.SysUser;
import com.app.timetable.mapper.SysUserMapper;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.CookieUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

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

    @Override
    public void register(SysUser sysUser, HttpServletResponse response) throws Exception {
        userMapper.insert(sysUser);
        String token = CookieUtil.getUUToken();
        CookieUtil.setTokenCookie(CommonContent.TOKEN_KEY,token, (int) CommonContent.LOGIN_EXPIRE_TIME, response);
    }
}
