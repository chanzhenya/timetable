package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * judithchen
 * 2019/4/13
 * Description：
 **/
@Getter
public enum CourseStatus {
    UNPUBLISHED(0,"待发布"),
    PUBLISHED(1,"已发布"),
    SHELF(2,"下架");

    private Integer code;

    private String message;

    CourseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
