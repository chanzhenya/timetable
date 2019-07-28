package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/4/15 10:45
 * @description
 */
@Getter
public enum TimetableStatus {
    INVALID(0,"已上课"),
    VALID(1,"未上课"),
    LEAVE(2,"请假"),
    TRUANCY(3,"旷课");

    private Integer code;

    private String message;

    TimetableStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
