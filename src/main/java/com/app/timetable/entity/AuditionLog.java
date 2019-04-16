package com.app.timetable.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-16
 */
public class AuditionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String courseId;

    private String teacherId;

    private String studentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AuditionLog{" +
        "id=" + id +
        ", courseId=" + courseId +
        ", teacherId=" + teacherId +
        ", studentId=" + studentId +
        "}";
    }
}
