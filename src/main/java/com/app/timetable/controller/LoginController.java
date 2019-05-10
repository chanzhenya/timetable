package com.app.timetable.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.annotation.UserLoginToken;
import com.app.timetable.entity.SysUser;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ApiAuthUtil;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
        try {
            Map<String,String> params = new HashMap<>();
            params.put("appid", CommonContent.APP_ID);
            params.put("secret",CommonContent.APP_SECRET);
            params.put("js_code",code);
            params.put("grant_type","authorization_code");
            String result = restTemplate.getForObject(CommonContent.WX_LOGIN_URL,String.class,params);
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
            String token = ApiAuthUtil.getToken(sysUser,session_key);
            return ResultVoUtil.success(sysUser, token, "登录成功");
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
