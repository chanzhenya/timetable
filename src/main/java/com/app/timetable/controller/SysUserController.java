package com.app.timetable.controller;


import com.app.timetable.entity.SysUser;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/register")
    public ResultVo register(@RequestBody Map<String,Object> params, HttpServletResponse response) {
        try {
            SysUser sysUser = initParam(params);
            userService.register(sysUser, response);
            return ResultVoUtil.success("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    private SysUser initParam(Map<String,Object> params) {
        String account = (String) params.get("accout"); // 微信号
        String openId = (String) params.get("openId"); //微信的openid
        String username = (String) params.get("username"); //微信名
        String name = (String) params.get("name"); // 用户真实名称
        String phone = (String) params.get("phone"); // 联系电话
        String imgUrl = (String) params.get("imgUrl"); // 微信头像
        Integer gender = (Integer) params.get("gender"); // 性别
        String id = (String) params.get("id");

        SysUser sysUser = new SysUser();
        if(!StringUtils.isNotBlank(id)) {
            id = ClassObjectUtils.getUUID();
        }

        sysUser.setId(id);
        sysUser.setAccount(account);
        sysUser.setOpenId(openId);
        sysUser.setUsename(username);
        sysUser.setName(name);
        sysUser.setPhone(phone);
        sysUser.setImgUrl(imgUrl);
        sysUser.setGender(gender);

        return sysUser;
    }
}

