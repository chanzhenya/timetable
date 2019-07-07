package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.annotation.UserLoginToken;
import com.app.timetable.entity.SysUser;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 登录
     * @param code
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestParam("code") String code) {
        String uri = String.format(CommonContent.WX_LOGIN_URL, CommonContent.APP_ID, CommonContent.APP_SECRET, code);
        String result = restTemplate.getForObject(uri,String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openId = jsonObject.getString("openid");
        String session_key = jsonObject.getString("session_key");
        String unionid = jsonObject.getString("unionid");
        SysUser sysUser = userService.selectByOpenId(openId);
        if(sysUser == null) {
            sysUser = new SysUser();
            sysUser.setId(ClassObjectUtils.getUUID());
            sysUser.setOpenId(openId);
            sysUser.setUnionId(unionid);
            sysUser.setUserType(UserType.TOURIST.getCode());
            userService.save(sysUser);
        }
        return ResultVoUtil.success(sysUser,null, "登录成功");
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public ResultVo getMessage() {
        return ResultVoUtil.success("验证通过");
    }
}
