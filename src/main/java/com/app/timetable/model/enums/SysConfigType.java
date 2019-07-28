package com.app.timetable.model.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/7/7 16:05
 * @description
 */
@Getter
public enum  SysConfigType {

    LEAVE_NUMBER("LEAVE_NUMBER","可请假次数"),
    BEFORE_COURSE_DUE_TIME("BEFORE_COURSE_DUE_TIME","课程到期前提醒时间");

    private String key;

    private String value;

    SysConfigType(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
