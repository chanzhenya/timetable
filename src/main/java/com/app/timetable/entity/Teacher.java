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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师ID
     */
    private String id;

    /**
     * 教师名称
     */
    private String name;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 教师简介，500字以内
     */
    private String description;

    /**
     * 教师标签，主要传授什么课程
     */
    private String tag;

    /**
     * 教师综合评分
     */
    private Integer score;

    /**
     * 照片
     */
    private String photo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户信息
     */
    private SysUser sysUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        "id=" + id +
        ", name=" + name +
        ", userId=" + userId +
        ", description=" + description +
        ", tag=" + tag +
        ", score=" + score +
        ", photo=" + photo +
        ", createTime=" + createTime +
        "}";
    }
}
