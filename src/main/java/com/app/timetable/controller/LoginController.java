package com.app.timetable.controller;

import com.app.timetable.annotation.UserLoginToken;
import com.app.timetable.entity.SysUser;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ApiAuthUtil;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Judith
 * @date 2019/4/29 16:10
 * @description
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private ISysUserService userService;

    /**
     * 登录
     * @param openId
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestParam("openId") String openId) {
        try {
            SysUser sysUser = userService.selectByOpenId(openId);
            if(sysUser != null) {
                String token = ApiAuthUtil.getToken(sysUser);
                return ResultVoUtil.success(sysUser, token, "登录成功");
            } else {
                return ResultVoUtil.error("不存在改用户信息，请重新登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public ResultVo getMessage() {
        return ResultVoUtil.success("验证通过");
    }
}
