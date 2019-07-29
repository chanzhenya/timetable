package com.app.timetable.mapper;

import com.app.timetable.model.dto.TeacherEvaluationDTO;
import com.app.timetable.model.entity.TeacherEvaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface TeacherEvaluationMapper extends BaseMapper<TeacherEvaluation> {

    IPage<TeacherEvaluationDTO> selectByPage(Page<TeacherEvaluationDTO> page, Map<String,Object> params);
}
