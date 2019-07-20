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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 微信号
     */
    private String account;

    /**
     * 用户的微信open ID
     */
    private String openId;

    private String unionId;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 微信头像
     */
    private String imgUrl;

    /**
     * 个人头像
     */
    private String photoUrl;

    /**
     * 性别，0-女；1-男
     */
    private Integer gender;

    /**
     * 学生积分/教师综合评分
     */
    private Double score;

    /**
     * 用户token
     */
    private String accessToken;

    /**
     * 用户类型：0-管理员；1-教师；2-学生；3-游客
     */
    private Integer userType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 个人简介
     */
    private String description;

    /**
     * 已上课课时
     */
    private Integer period;

    /**
     * 旷课课时
     */
    private Integer truancy;

    /**
     * 请假次数
     */
    private Integer leaveNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Double getScore() {
        return score != null ? score : 0;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriod() {
        return period != null ? period : 0;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getTruancy() {
        return truancy != null ? truancy : 0;
    }

    public void setTruancy(Integer truancy) {
        this.truancy = truancy;
    }

    public Integer getLeaveNum() {
        return leaveNum != null ? leaveNum : 0;
    }

    public void setLeaveNum(Integer leaveNum) {
        this.leaveNum = leaveNum;
    }
}
