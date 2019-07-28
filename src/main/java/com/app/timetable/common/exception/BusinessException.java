package com.app.timetable.common.exception;

import com.app.timetable.common.enums.ExceptionCodeEnum;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3560035153177546041L;
    /**错误码*/
    private ExceptionCodeEnum code;
    /**错误描述*/
    private String msg;

    public ExceptionCodeEnum getCode() {
        return code;
    }

    public void setCode(ExceptionCodeEnum code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BusinessException() {
        super();
        this.code = ExceptionCodeEnum.BIZ_EXCEPTION;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = ExceptionCodeEnum.BIZ_EXCEPTION;
        this.msg = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ExceptionCodeEnum.BIZ_EXCEPTION;
        this.msg = message;
    }

    public BusinessException(String message) {
        super(message);
        this.code = ExceptionCodeEnum.BIZ_EXCEPTION;
        this.msg = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.code = ExceptionCodeEnum.BIZ_EXCEPTION;
    }
}
