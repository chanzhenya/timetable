package com.app.timetable.controller;


import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.Teacher;
import com.app.timetable.exception.MyException;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ITeacherService teacherService;

    @PostMapping("/add")
    public ResultVo add(@RequestBody Map<String,Object> params) {
        String userId = ClassObjectUtils.getUUID(); //用户信息ID
        String teacherId = ClassObjectUtils.getUUID(); // 教师信息ID
        SysUser sysUser = initUser(params);
        sysUser.setId(userId);

        Teacher teacher = new Teacher();
        return ResultVoUtil.success(null);
    }

    private SysUser initUser(Map<String,Object> params) {
        String account = (String) params.get("accout"); // 微信号
        String openId = (String) params.get("openId"); //微信的openid
        String username = (String) params.get("username"); //微信名
        String name = (String) params.get("name"); // 用户真实名称
        String phone = (String) params.get("phone"); // 联系电话
        String imgUrl = (String) params.get("imgUrl"); // 微信头像
        Integer gender = (Integer) params.get("gender"); // 性别

        boolean flag = true;
        SysUser sysUser = new SysUser();
        if(StringUtils.isNotBlank(account)) {
            sysUser.setAccount(account);
        } else {
            flag = false;
        }
        if(StringUtils.isNotBlank(openId)) {
            sysUser.setOpenId(openId);
        } else {
            flag = false;
        }
        if(StringUtils.isNotBlank(username)) {
            sysUser.setUsename(username);
        }
        if(StringUtils.isNotBlank(name)) {
            sysUser.setName(name);
        }
        if(StringUtils.isNotBlank(phone)) {
            sysUser.setPhone(phone);
        } else {
            flag = false;
        }
        if(StringUtils.isNotBlank(imgUrl)) {
            sysUser.setImgUrl(imgUrl);
        }
        if(gender != null) {
            sysUser.setGender(gender);
        } else {
            flag = false;
        }

        if(!flag) {
            throw new MyException(10,"缺少必要字段");
        }

        return sysUser;
    }
}

