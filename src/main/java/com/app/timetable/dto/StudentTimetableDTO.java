package com.app.timetable.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Judith
 * @date 2019/4/15 16:13
 * @description
 */
@Data
public class StudentTimetableDTO {

    /**
     * id
     */
    private String id;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 学生名称
     */
    private String studentName;

    /**
     * 微信头像
     */
    private String imgUrl;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 上课时间
     */
    private LocalDateTime courseTime;

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
