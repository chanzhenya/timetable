package com.app.timetable.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.app.timetable.common.model.BaseService;
import com.app.timetable.model.dto.PurchasedCourseDTO;
import com.app.timetable.model.dto.SysUserDTO;
import com.app.timetable.model.entity.StudentPurchasedCourse;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.mapper.SysUserMapper;
import com.app.timetable.model.enums.UserType;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class SysUserServiceImpl extends BaseService<SysUserMapper, SysUser> implements ISysUserService {

    Log log = LogFactory.get();

    @Autowired
    private IStudentPurchasedCourseService purchasedCourseService;

    @Override
    public IPage<SysUserDTO> selecBytPage(Map<String,Object> params) {
        Page<SysUserDTO> page = initPage(params);
        return baseMapper.selectByPage(page,params);
    }

    @Override
    public JSONObject studetnDetail(String userId) {
        Map<String,Object> _map = new HashMap<>();
        _map.put("studentId",userId);
        _map.put("status",1);
        List<PurchasedCourseDTO> list = purchasedCourseService.selectByPage(_map).getRecords();
        String teachers = "";
        Integer truancyNum = 0; // 旷课次数
        Integer leaveNum = 0; // 请假次数
        for(PurchasedCourseDTO dto : list) {
            if(StringUtils.isNotBlank(teachers)) {
                teachers += ", ";
            }
            teachers += dto.getTeacherName();
            truancyNum += dto.getTruancyNum()!=null?dto.getTruancyNum():0;
            leaveNum += dto.getLeaveNum()!=null?dto.getLeaveNum():0;
        }
        JSONObject result = new JSONObject();
        result.put("teachers",teachers);
        result.put("truancyNum",truancyNum);
        result.put("leaveNum",leaveNum);
        return result;
    }

    @Override
    public IPage<SysUserDTO> selectMyStudents(int pageNum, int pageSize, StudentPurchasedCourse purchasedCourse) {
        Page<SysUserDTO> page = new Page<>(pageNum,pageSize);
        return baseMapper.selectMyStudents(page, purchasedCourse);
    }

    @Override
    public List<SysUserDTO> teacherOptions(String tagId) {
        return baseMapper.teacherOptions(tagId);
    }

    @Override
    public SysUser saveSysUser(String openId) {
        log.info("新增用户信息, openId：{}",openId);

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",openId);
        SysUser sysUser = getOne(queryWrapper);
        if(sysUser == null) {

            sysUser = new SysUser();
            sysUser.setOpenId(openId);
            sysUser.setUserType(UserType.TOURIST.getCode());
            save(sysUser);
        }
        return sysUser;
    }

    @Override
    public SysUser getByOpenId(String openId) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",openId);
        return getOne(queryWrapper);
    }
}
