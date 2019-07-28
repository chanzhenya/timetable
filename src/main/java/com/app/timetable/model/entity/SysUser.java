package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Data
public class SysUser extends BaseModel {

    private static final long serialVersionUID = 1L;

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
}
