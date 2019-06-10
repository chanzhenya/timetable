package com.app.timetable.service.impl;

import com.app.timetable.entity.StudentPurchasedCourse;
import com.app.timetable.entity.SysConfig;
import com.app.timetable.mapper.SysConfigMapper;
import com.app.timetable.service.IStudentPurchasedCourseService;
import com.app.timetable.service.ISysConfigService;
import com.app.timetable.utils.ClassObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
@Service
@Transactional
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Autowired
    private IStudentPurchasedCourseService studentPurchasedCourseService;

    @Override
    public void config(int number) {
        List<SysConfig> sysConfigs = list();
        SysConfig sysConfig = null;
        if(sysConfigs.isEmpty()) {
            sysConfig = new SysConfig();
            sysConfig.setId(ClassObjectUtils.getUUID());
            sysConfig.setCreateTime(LocalDateTime.now());
        } else {
            sysConfig = sysConfigs.get(0);
        }
        sysConfig.setNumber(number);
        sysConfig.setDescription("可请假次数");
        saveOrUpdate(sysConfig);

        //同步更新学生已购买课程剩余请假次数
        List<StudentPurchasedCourse> list = studentPurchasedCourseService.list();
        for(StudentPurchasedCourse purchasedCourse:list) {
            int remainNum = Math.max(0,number-purchasedCourse.getLeaveNum());
            purchasedCourse.setRemainNum(remainNum);
        }
        studentPurchasedCourseService.updateBatchById(list);
    }

    @Override
    public SysConfig getConfig() {
        List<SysConfig> sysConfigs = list();
        return sysConfigs.size() > 0 ? sysConfigs.get(0) : null;
    }
}
