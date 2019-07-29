package com.app.timetable.controller;


import com.alibaba.fastjson.JSONObject;
import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.entity.Picture;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.model.enums.UserType;
import com.app.timetable.service.*;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IPictureService pictureService;

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/uploadFile")
    public ResultVo uploadFile(@RequestParam(value = "img")MultipartFile multipartFile,
                               @RequestParam(value = "photoUrl", required = false) String photoUrl) {
        if(StringUtils.isNotBlank(photoUrl)) {
            pictureService.delete(photoUrl);
        }
        Picture picture = pictureService.uploadFile(multipartFile);
        return ResultVoUtil.success(picture.getImgUrl());
    }

    /**
     * 用户注册
     * @param imgUrl
     * @param openId
     * @param username
     * @param name
     * @param phone
     * @param gender
     * @param description
     * @param userType
     * @param response
     * @return
     */
    @PostMapping("/register")
    public ResultVo register(@RequestParam(value = "photoUrl", required = false)String photoUrl,
                             @RequestParam(value = "imgUrl", required = false) String imgUrl,
                             @RequestParam("openId") String openId, @RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "name", required = false) String name, @RequestParam("phone") String phone,
                             @RequestParam(value = "gender",required = false) Integer gender, @RequestParam(value = "description", required = false)  String description,
                             @RequestParam("userType") Integer userType, HttpServletResponse response) {
        SysUser sysUser = userService.selectByOpenId(openId);
        if(sysUser == null) {
            sysUser = new SysUser();
        }
        sysUser.setAccount(username);
        sysUser.setImgUrl(imgUrl);
        sysUser.setOpenId(openId);
        sysUser.setGender(gender);
        sysUser.setUsername(username);
        sysUser.setName(name);
        sysUser.setPhone(phone);
        sysUser.setDescription(description);
        sysUser.setUserType(userType);
        sysUser.setPhotoUrl(photoUrl);
        userService.register(sysUser, response);
        return ResultVoUtil.success("注册成功");
    }

    /**
     * 用户列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(userService.selecBytPage(params));
    }

    /**
     * 用户详情
     * @param userId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("userId") String userId) {
        JSONObject result = new JSONObject();
        SysUser sysUser = userService.getById(userId);
        JSONObject extendObj = new JSONObject();

        if(UserType.STUDENT.getCode().equals(sysUser.getUserType())) {
            extendObj = userService.studetnDetail(userId);
        } else if(UserType.TEACHER.getCode().equals(sysUser.getUserType())) {

        }
        result.put("userInfo",sysUser);
        result.put("extendData",extendObj);
        return ResultVoUtil.success(result);
    }

    /**
     * 修改用户个人信息
     * @param userId
     * @param userType
     * @param name
     * @param phone
     * @param photoUrl
     * @param description
     * @return
     */
    @PostMapping("/update")
    public ResultVo update(@RequestParam("userId") Long userId,
                           @RequestParam(value = "userType", required = false) int userType,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "phone", required = false) String phone,
                           @RequestParam(value = "photoUrl", required = false) String photoUrl,
                           @RequestParam(value = "description", required = false) String description) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUserType(userType);
        sysUser.setName(name);
        sysUser.setPhotoUrl(photoUrl);
        sysUser.setPhone(phone);
        sysUser.setDescription(description);
        userService.updateById(sysUser);
        return ResultVoUtil.success("更新成功");
    }
}

