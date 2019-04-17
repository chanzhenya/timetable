package com.app.timetable.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Judith
 * @date 2019/4/17 9:29
 * @description
 */
@Data
public class TeacherEvaluationDTO {


    private String id;

    private String studentId;

    private String studentAccount;

    private String teacherId;

    private double score;

    private String content;

    private LocalDateTime createTime;
}
