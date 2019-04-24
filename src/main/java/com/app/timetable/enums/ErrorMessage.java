package com.app.timetable.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/4/24 16:42
 * @description
 */
@Getter
public enum ErrorMessage {

    ERROR_MSG_1(501,"课程取消失败，您在本课程的可用请假次数已用完");

    private Integer code;

    private String message;

    ErrorMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
