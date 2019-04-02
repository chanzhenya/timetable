package com.app.timetable.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public class StudentTimtable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 上课时间
     */
    private LocalDateTime courseTime;

    /**
     * 状态，0-请假，1-旷课，2-已参与上课
     */
    private Integer status;

    /**
     * 课后作业
     */
    private String homework;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(LocalDateTime courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StudentTimtable{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseId=" + courseId +
        ", courseTime=" + courseTime +
        ", status=" + status +
        ", homework=" + homework +
        ", createTime=" + createTime +
        "}";
    }
}
