package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Data
public class StudentPurchasedCourse extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 学生id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentId;

    /**
     * 课程id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;

    /**
     * 教师ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    /**
     * 旷课次数
     */
    private Integer truancyNum;

    /**
     * 请假次数
     */
    private Integer leaveNum;

    /**
     * 剩余请假次数
     */
    private Integer remainNum;

    /**
     * 课时余量
     */
    private Integer remain;

    /**
     * 课程到期时间
     */
    private Date dueTime;

    /**
     * 状态，0-失效，1-有效
     */
    private Integer status;
}
