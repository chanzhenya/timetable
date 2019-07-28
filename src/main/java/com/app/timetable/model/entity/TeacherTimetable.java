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
public class TeacherTimetable extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    /**
     * 课程ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;

    /**
     * 学生人数
     */
    private Integer number;

    /**
     * 课后作业
     */
    private String homework;

    /**
     * 上课日期
     */
    private String courseTime;

    /**
     * 上课时间
     */
    private String startTime;

    /**
     * 下课时间
     */
    private String endTime;

    /**
     * 状态，1-未上课，0-已上课
     */
    private Integer status;
}
