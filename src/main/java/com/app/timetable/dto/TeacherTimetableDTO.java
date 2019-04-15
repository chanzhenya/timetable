package com.app.timetable.dto;

import com.app.timetable.entity.StudentTimtable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Judith
 * @date 2019/4/15 10:05
 * @description
 */
@Data
public class TeacherTimetableDTO {

    private String id;

    private String courseId;

    private String courseName;

    private String teacherId;

    private String teacherName;

    private Integer number;

    private String homework;

    private LocalDateTime courseTime;

    private List<StudentTimtable> students = new ArrayList<>();
}
