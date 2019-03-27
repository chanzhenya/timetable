package com.app.timetable.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-03-27
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String usename;

    /**
     * 用户的微信open ID
     */
    private String openId;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 微信头像
     */
    private String imgUrl;

    /**
     * 性别，0-女；1-男
     */
    private Integer gender;

    /**
     * 用户token
     */
    private String accessToken;

    /**
     * 用户类型：0-管理员；1-教师；2-学生
     */
    private Integer userType;

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

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", usename=" + usename +
        ", openId=" + openId +
        ", phone=" + phone +
        ", imgUrl=" + imgUrl +
        ", gender=" + gender +
        ", accessToken=" + accessToken +
        ", userType=" + userType +
        ", createTime=" + createTime +
        "}";
    }
}
