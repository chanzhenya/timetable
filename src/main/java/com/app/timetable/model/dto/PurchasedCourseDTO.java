package com.app.timetable.model.dto;

import lombok.Data;

/**
 * judithchen
 * 2019/4/13
 * Description：
 **/
@Data
public class PurchasedCourseDTO {

    private String id;

    private String studentId;

    private String courseId;

    private String courseName;

    private String teacherId;

    private String teacherName;

    private Integer remain;

    private String dueTime;

    private Integer status;

    private String createTime;

    /**
     * 距离截止日期还有多少天
     */
    private Long countDownDays;

    /**
     * 旷课次数
     */
    private Integer truancyNum;

    /**
     * 请假次数
     */
    private Integer leaveNum;

    /**
     * 剩余可请假次数
     */
    private Integer remainNum;
}
