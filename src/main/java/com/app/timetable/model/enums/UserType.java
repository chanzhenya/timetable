package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * judithchen
 * 2019/3/30
 * Description：
 **/
@Getter
public enum UserType {
    ADMIN(0,"管理员"),
    TEACHER(1,"教师"),
    STUDENT(2,"学生"),
    TOURIST(3,"游客");

    UserType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;
}
