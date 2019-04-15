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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 旷课次数
     */
    private Integer truancyNum;

    /**
     * 请假次数
     */
    private Integer leaveNum;

    /**
     * 积分
     */
    private Integer score;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTruancyNum() {
        return truancyNum;
    }

    public void setTruancyNum(Integer truancyNum) {
        this.truancyNum = truancyNum;
    }

    public Integer getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Integer leaveNum) {
        this.leaveNum = leaveNum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Student{" +
        "id=" + id +
        ", userId=" + userId +
        ", truancyNum=" + truancyNum +
        ", leaveNum=" + leaveNum +
        ", score=" + score +
        ", createTime=" + createTime +
        "}";
    }
}
