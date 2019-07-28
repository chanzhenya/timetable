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
public class StudentTimtable extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentId;

    /**
     * 教师id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    /**
     * 课程id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;

    /**
     * 上课时间
     */
    private String courseTime;

    private String startTime;

    private String endTime;

    /**
     * 课程类型
     * 0-正式课程
     * 1-试听课程
     */
    private Integer courseType;

    /**
     * 状态，0-请假，1-旷课，2-上课
     */
    private Integer status;

    /**
     * 课后作业
     */
    private String homework;
}
