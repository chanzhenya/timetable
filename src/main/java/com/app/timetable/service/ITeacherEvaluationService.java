package com.app.timetable.service;

import com.app.timetable.model.dto.TeacherEvaluationDTO;
import com.app.timetable.model.entity.TeacherEvaluation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ITeacherEvaluationService extends IService<TeacherEvaluation> {

    IPage<TeacherEvaluationDTO> selectByPage(Map<String,Object> params);

    TeacherEvaluation saveTeacherEvaluation(TeacherEvaluation evaluation);
}
