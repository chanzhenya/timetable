package com.app.timetable.common.utils;

import cn.hutool.core.map.MapUtil;
import com.app.timetable.common.exception.BusinessException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Judith
 * @date 2019/7/7 15:29
 * @description
 */
public class BaseUtils {

    private BaseUtils(){}
    private static class SingletonHolder{
        private static final BaseUtils sInstance = new BaseUtils();
    }
    public static BaseUtils getInstance(){
        return SingletonHolder.sInstance;
    }

    public Map<String,Object> checkParams(Map<String,Object> params,String[] keys){
        Map<String,Object> tparams = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (!params.containsKey(keys[i]))
                throw new BusinessException("参数" + keys[i] + "缺失!");
            else
                tparams.put(keys[i],params.get(keys[i]));
        }
        return tparams;
    }

    public Page initPage(Map<String,Object> params){
        int pageNum = 1 ;
        int pageSize = 10;
        boolean queryAll = false;
        if(params.containsKey("pageNum")) {
            pageNum = MapUtil.getInt(params,"pageNum");
        }

        if(params.containsKey("pageSize")){
            pageSize = MapUtil.getInt(params,"pageSize");
        }else {
            pageSize = Integer.MAX_VALUE;
        }

        return new Page<>(pageNum,pageSize);
    }
}
