package com.app.timetable.dto;

import com.app.timetable.entity.Student;
import com.app.timetable.entity.Teacher;
import lombok.Data;

/**
 * judithchen
 * 2019/4/11
 * Description：
 **/
@Data
public class SysUserDetailDTO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String usename;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 微信号
     */
    private String account;

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
     * 用户类型：0-管理员；1-教师；2-学生
     */
    private Integer userType;

    /**
     * 简介
     */
    private String description;

    /**
     * 教师信息
     */
    private Teacher teacher;

    /**
     * 学生信息
     */
    private Student student;
}
