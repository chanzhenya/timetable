package com.app.timetable.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
public class StudentPurchasedCourse implements Serializable {

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
     * 教师ID
     */
    private String teacherId;

    /**
     * 课时余量
     */
    private Integer remain;

    /**
     * 课程到期时间
     */
    private LocalDateTime dueTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态，0-失效，1-有效
     */
    private Integer status;


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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentPurchasedCourse{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseId=" + courseId +
        ", teacherId=" + teacherId +
        ", remain=" + remain +
        ", dueTime=" + dueTime +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
