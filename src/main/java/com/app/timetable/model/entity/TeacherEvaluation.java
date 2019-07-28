package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Data
public class TeacherEvaluation extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentId;

    /**
     * 教师id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    /**
     * 评分
     */
    private double score;

    /**
     * 评价内容
     */
    private String content;
}
