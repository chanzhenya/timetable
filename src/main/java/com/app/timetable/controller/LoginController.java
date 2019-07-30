package com.app.timetable.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.model.enums.UserType;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
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
     *
     * @param code
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestParam("code") String code) {
        String uri = String.format(CommonContent.WX_LOGIN_URL, CommonContent.APP_ID, CommonContent.APP_SECRET, code);
        String result = HttpUtil.get(uri);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(jsonObject.containsKey("errcode")) {
            return ResultVoUtil.error(jsonObject.getString("errmsg"));
        }

        String openId = jsonObject.getString("openid");
        SysUser sysUser = userService.saveSysUser(openId);
        return ResultVoUtil.success(sysUser, "登录成功");
    }
}
