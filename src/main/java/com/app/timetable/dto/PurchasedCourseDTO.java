package com.app.timetable.dto;

import com.app.timetable.entity.Course;
import lombok.Data;

import java.time.LocalDateTime;

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

    private LocalDateTime dueTime;

    private Integer status;

    private LocalDateTime createTime;

    /**
     * 距离截止日期还有多少天
     */
    private Long countDownDays;
}
