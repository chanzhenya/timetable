package com.app.timetable.controller;


import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.SysUserDTO;
import com.app.timetable.entity.Picture;
import com.app.timetable.entity.SysUser;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.*;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

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
    private UploadFileService uploadFileService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ITeacherEvaluationService evaluationService;

    @Autowired
    private IStudentPurchasedCourseService purchasedCourseService;

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/uploadFile")
    public ResultVo uploadFile(@RequestParam(value = "img")MultipartFile multipartFile,
                               @RequestParam(value = "photoUrl", required = false) String photoUrl) {
        if(StringUtils.isNotBlank(photoUrl)) {
            uploadFileService.delete(photoUrl);
        }
        Picture picture = uploadFileService.uploadFile(multipartFile);
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
        sysUser.setCreateTime(LocalDateTime.now());
        userService.register(sysUser, response);
        return ResultVoUtil.success("注册成功");
    }

    /**
     * 用户列表
     * @param pageNum
     * @param pageSize
     * @param userType
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize,
                         @RequestParam(value = "userType", required = false) Integer userType) {
        SysUser sysUser = new SysUser();
        sysUser.setUserType(userType);
        IPage<SysUserDTO> page = userService.selectPage(pageNum,pageSize,sysUser);
        return ResultVoUtil.success(page);
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
    public ResultVo update(@RequestParam("userId") String userId,
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

