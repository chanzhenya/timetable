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
public class TeacherTimetable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师课表id
     */
    private String id;

    /**
     * 教师id
     */
    private String teacherId;

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 课程类型：1-正式课程；2-试听课程
     */
    private Integer courseType;

    private Integer number;

    /**
     * 课后作业
     */
    private String homework;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TeacherTimetable{" +
        "id=" + id +
        ", teacherId=" + teacherId +
        ", courseId=" + courseId +
        ", courseType=" + courseType +
        ", number=" + number +
        ", homework=" + homework +
        ", updateTime=" + updateTime +
        ", createTime=" + createTime +
        "}";
    }
}
