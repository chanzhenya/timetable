package com.app.timetable.utils;

import com.app.timetable.exception.MyException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Judith
 * @date 2019/7/7 15:29
 * @description
 */
public class BaseUtils {

    public static Map<String,Object> checkParams(Map<String,Object> params, String[] keys){
        Map<String,Object> tparams = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (!params.containsKey(keys[i]))
                throw new MyException(4001,"参数" + keys[i] + "缺失!");
            else
                tparams.put(keys[i],params.get(keys[i]));
        }
        return tparams;
    }
}
