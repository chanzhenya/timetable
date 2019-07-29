package com.app.timetable.model.dto;

import com.app.timetable.model.entity.TeacherEvaluation;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Judith
 * @date 2019/4/17 9:29
 * @description
 */
@Data
public class TeacherEvaluationDTO extends TeacherEvaluation {

    private String studentAccount;
}
