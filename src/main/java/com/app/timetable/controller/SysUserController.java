package com.app.timetable.controller;


import com.alibaba.fastjson.JSONObject;
import com.app.timetable.dto.SysUserDTO;
import com.app.timetable.dto.SysUserDetailDTO;
import com.app.timetable.dto.TeacherEvaluationDTO;
import com.app.timetable.entity.Course;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.TeacherEvaluation;
import com.app.timetable.enums.UserType;
import com.app.timetable.service.ICourseService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherEvaluationService;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 用户注册
     * @param multipartFile
     * @param account
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
    public ResultVo register(@RequestParam(value = "photo", required = false)MultipartFile multipartFile,
                             @RequestParam("account") String account, @RequestParam(value = "imgUrl", required = false) String imgUrl,
                             @RequestParam("openId") String openId, @RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "name", required = false) String name, @RequestParam("phone") String phone,
                             @RequestParam(value = "gender",required = false) Integer gender, @RequestParam(value = "description", required = false)  String description,
                             @RequestParam("userType") Integer userType, HttpServletResponse response) {
        try {
            String photoUrl = uploadFileService.uploadFile(multipartFile);
            SysUser sysUser = new SysUser();
            sysUser.setId(ClassObjectUtils.getUUID());
            sysUser.setAccount(account);
            sysUser.setImgUrl(imgUrl);
            sysUser.setOpenId(openId);
            sysUser.setGender(gender);
            sysUser.setUsename(username);
            sysUser.setName(name);
            sysUser.setPhone(phone);
            sysUser.setDescription(description);
            sysUser.setUserType(userType);
            sysUser.setPhotoUrl(photoUrl);
            sysUser.setCreateTime(LocalDateTime.now());
            userService.register(sysUser, response);
            return ResultVoUtil.success("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
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
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                         @RequestParam(value = "userType", required = false) Integer userType) {
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserType(userType);
            IPage<SysUserDTO> page = userService.selectPage(pageNum,pageSize,sysUser);
            return ResultVoUtil.success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 用户详情
     * @param userId
     * @return
     */
    @PostMapping("/detail")
    public ResultVo detail(@RequestParam("userId") String userId) {
        try {
            JSONObject result = new JSONObject();
            SysUserDetailDTO sysUser = userService.getUserDetail(userId);
            if(UserType.TEACHER.getCode().equals(sysUser.getUserType())) {
                IPage<Course> courseIPage = courseService.selectPage(1,20,sysUser.getId());
                List<Course> courseList = courseIPage.getRecords();

                TeacherEvaluation evaluation = new TeacherEvaluation();
                evaluation.setTeacherId(sysUser.getId());
                IPage<TeacherEvaluationDTO> evaluationDTOIPage = evaluationService.selectByPage(1,10,evaluation);
                List<TeacherEvaluationDTO> evaluationDTOS = evaluationDTOIPage.getRecords();
                result.put("courses",courseList);
                result.put("evaluationS",evaluationDTOS);
            }
            result.put("user", sysUser);
            return ResultVoUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}

