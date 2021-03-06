package com.app.timetable.common.model;

import cn.hutool.core.map.MapUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.app.timetable.common.constant.SysCommonConstant;
import com.app.timetable.common.utils.BaseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Judith
 * @date 2019/7/30 19:48
 * @description
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

    final protected Log logger = LogFactory.get();
    final Class<T> tClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    public Page initPage(Map<String,Object> params){
        BaseUtils.getInstance().removeNullEntry(params);
        int pageNum = SysCommonConstant.PAGE_NUM_VALUE;
        int pageSize = Integer.MAX_VALUE;

        if(params.containsKey(SysCommonConstant.PAGE_NUM)) {
            pageNum = MapUtil.getInt(params,SysCommonConstant.PAGE_NUM);
        }
        if(params.containsKey(SysCommonConstant.PAGE_SIZE)){
            pageSize = MapUtil.getInt(params,SysCommonConstant.PAGE_SIZE);
        }

        return new Page<>(pageNum,pageSize);
    }

    @Override
    public T getById(Serializable id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysCommonConstant.ID,id);
        queryWrapper.eq(SysCommonConstant.ENABLE,SysCommonConstant.VALID);
        return super.getOne(queryWrapper);
    }
}
