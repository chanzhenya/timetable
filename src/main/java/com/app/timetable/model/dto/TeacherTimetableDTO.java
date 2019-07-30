package com.app.timetable.model.dto;

import com.app.timetable.model.entity.TeacherTimetable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Judith
 * @date 2019/4/15 10:05
 * @description
 */
@Data
public class TeacherTimetableDTO extends TeacherTimetable {

    private String courseName;

    private String teacherName;

    private List<StudentTimetableDTO> students = new ArrayList<>();
}
