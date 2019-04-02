package com.app.timetable.enums;

import lombok.Getter;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
@Getter
public enum ResultCode {

    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
