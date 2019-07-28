package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/4/15 10:07
 * @description
 */
@Getter
public enum  CourseType {
    FORMAL(0,"正式课程"),
    AUDITION(1,"试听课程");

    private Integer code;

    private String message;

    CourseType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
