package com.app.timetable.model.vo;

import lombok.Data;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
@Data
public class ResultVo {

    private boolean status;

    private int code;

    private String msg;

    private Object data;

    private String token;
}
