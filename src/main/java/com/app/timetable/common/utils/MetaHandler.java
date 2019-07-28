package com.app.timetable.common.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MetaHandler implements MetaObjectHandler {

    /**
     * 插入操作：自动填写
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object enable = getFieldValByName("enable", metaObject);
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);

        if(enable == null){
            setFieldValByName("enable",1,metaObject);
        }
        if(createTime == null){
            setFieldValByName("createTime",new Date(),metaObject);
        }
        if(updateTime == null) {
            setFieldValByName("updateTime",new Date(),metaObject);
        }
    }

    /**
     * 更新操作自动填写
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 获取到需要被填充的字段值
        setFieldValByName("updateTime",new Date(),metaObject);
    }
}
