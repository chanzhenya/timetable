package com.app.timetable.common.enums;

public enum ExceptionCodeEnum {

    /**成功*/
    OK("200", "success"),
    /**系统异常*/
    ERROR("201", "系统繁忙请稍后重试。"),
    /**数据库异常*/
    DATA_EXCEPTION("202", "系统繁忙请稍后重试。"),
    /**业务异常*/
    BIZ_EXCEPTION("203", "业务繁忙，请稍后再试。"),
    ;

    private String code;
    private String msg;

    ExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
