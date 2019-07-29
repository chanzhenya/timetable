package com.app.timetable.service.impl;

import com.app.timetable.common.utils.BaseUtils;
import com.app.timetable.model.dto.TeacherEvaluationDTO;
import com.app.timetable.model.entity.SysUser;
import com.app.timetable.model.entity.TeacherEvaluation;
import com.app.timetable.mapper.TeacherEvaluationMapper;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherEvaluationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TeacherEvaluationServiceImpl extends ServiceImpl<TeacherEvaluationMapper, TeacherEvaluation> implements ITeacherEvaluationService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public IPage<TeacherEvaluationDTO> selectByPage(Map<String,Object> params) {
        Page<TeacherEvaluationDTO> page = BaseUtils.getInstance().initPage(params);
        return baseMapper.selectByPage(page,params);
    }

    @Override
    public TeacherEvaluation saveTeacherEvaluation(TeacherEvaluation evaluation) {
        SysUser sysUser = sysUserService.getById(evaluation.getTeacherId());
        if(sysUser.getScore() > 0) {
            double score = (sysUser.getScore() * 2 + evaluation.getScore()) / 2.0; // 计算教师综合评分
            sysUser.setScore(score);
        } else {
            sysUser.setScore(evaluation.getScore());
        }
        sysUserService.updateById(sysUser);
        save(evaluation);
        return evaluation;
    }
}
