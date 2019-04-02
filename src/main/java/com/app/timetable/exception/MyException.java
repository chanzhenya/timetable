package com.app.timetable.exception;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author Judith
 * @date 2019/4/2 15:18
 * @description
 */
@Data
public class MyException extends RuntimeException {

    public MyException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;
}
