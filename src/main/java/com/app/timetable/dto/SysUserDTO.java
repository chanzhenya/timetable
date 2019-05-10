package com.app.timetable.dto;

import lombok.Data;

/**
 * judithchen
 * 2019/4/11
 * Description：
 **/
@Data
public class SysUserDTO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 微信号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户真实名称
     */
    private String name;

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
     * 用户类型：0-管理员；1-教师；2-学生
     */
    private Integer userType;
}
