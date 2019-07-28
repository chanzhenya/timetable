package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * judithchen
 * 2019/4/13
 * Description：
 **/
@Getter
public enum PurchasedCourseStatus {

    INVALID(0,"失效"),
    VALID(1,"有效");

    private Integer code;

    private String message;

    PurchasedCourseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
