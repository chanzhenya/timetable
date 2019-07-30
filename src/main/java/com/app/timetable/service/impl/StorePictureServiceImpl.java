package com.app.timetable.service.impl;

import com.app.timetable.common.model.BaseService;
import com.app.timetable.common.utils.BaseUtils;
import com.app.timetable.model.entity.StorePicture;
import com.app.timetable.mapper.StorePictureMapper;
import com.app.timetable.service.IStorePictureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Service
public class StorePictureServiceImpl extends BaseService<StorePictureMapper, StorePicture> implements IStorePictureService {

    @Override
    public IPage<StorePicture> selectByPage(Map<String,Object> params) {
        Page<StorePicture> page = initPage(params);
        QueryWrapper<StorePicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectPage(page,queryWrapper);
    }
}
