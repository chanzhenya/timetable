package com.app.timetable.service.impl;

import com.app.timetable.dto.TeacherEvaluationDTO;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.TeacherEvaluation;
import com.app.timetable.mapper.TeacherEvaluationMapper;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherEvaluationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TeacherEvaluationMapper evaluationMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public IPage<TeacherEvaluationDTO> selectByPage(int pageNum, int pageSize, TeacherEvaluation evaluation) {
        Page<TeacherEvaluationDTO> page = new Page<>(pageNum,pageSize);
        return evaluationMapper.selectByPage(page,evaluation);
    }

    @Override
    public void insert(TeacherEvaluation evaluation) {
        SysUser sysUser = sysUserService.getById(evaluation.getTeacherId());
        if(sysUser.getScore() > 0) {
            double score = (sysUser.getScore() * 2 + evaluation.getScore()) / 2.0; // 计算教师综合评分
            sysUser.setScore(score);
        } else {
            sysUser.setScore(evaluation.getScore());
        }
        sysUserService.updateById(sysUser);
        evaluationMapper.insert(evaluation);
    }
}
