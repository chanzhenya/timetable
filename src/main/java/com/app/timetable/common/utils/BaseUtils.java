package com.app.timetable.common.utils;

import cn.hutool.core.map.MapUtil;
import com.app.timetable.common.exception.BusinessException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;

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

    /*移除Map中值为空的键值对*/
    public void removeNullEntry(Map map) {
        removeNullKey(map);
        removeNullValue(map);
    }

    /*移除键为空的键值对*/
    public void removeNullKey(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = (Object) iterator.next();
            remove(obj, iterator);
        }
    }

    /*移除值为空的键值对*/
    public void removeNullValue(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = (Object) iterator.next();
            Object value = (Object) map.get(obj);
            remove(value, iterator);
        }
    }

    private void remove(Object obj, Iterator iterator) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (str == null || str.trim().isEmpty()) {
                iterator.remove();
            }
        } else if (obj instanceof Collection) {
            Collection col = (Collection) obj;
            if (col == null || col.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Map) {
            Map temp = (Map) obj;
            if (temp == null || temp.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Object[]) {
            Object[] array = (Object[]) obj;
            if (array == null || array.length <= 0) {
                iterator.remove();
            }
        } else {
            if (obj == null) {
                iterator.remove();
            }
        }
    }

    public boolean isEmpty(Object obj) {
       return obj == null || obj.toString().length() == 0;
    }
}
