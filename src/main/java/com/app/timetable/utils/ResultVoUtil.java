package com.app.timetable.utils;

import com.app.timetable.model.enums.ResultCode;
import com.app.timetable.model.vo.ResultVo;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
public class ResultVoUtil {

    public static ResultVo success(Object obj) {
        ResultVo resultVo = new ResultVo();
        resultVo.setStatus(true);
        resultVo.setCode(ResultCode.SUCCESS.getCode());
        resultVo.setMsg(ResultCode.SUCCESS.getMsg());
        resultVo.setData(obj);
        return resultVo;
    }

    public static ResultVo success(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setStatus(true);
        resultVo.setCode(ResultCode.SUCCESS.getCode());
        resultVo.setMsg(msg);
        resultVo.setData(null);
        return resultVo;
    }

    public static ResultVo success(Object obj, String token,String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setStatus(true);
        resultVo.setCode(ResultCode.SUCCESS.getCode());
        resultVo.setMsg(msg);
        resultVo.setData(obj);
        resultVo.setToken(token);
        return resultVo;
    }

    public static ResultVo error(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setStatus(false);
        resultVo.setCode(ResultCode.FAIL.getCode());
        resultVo.setMsg(msg);
        resultVo.setData(null);
        return resultVo;
    }
}
