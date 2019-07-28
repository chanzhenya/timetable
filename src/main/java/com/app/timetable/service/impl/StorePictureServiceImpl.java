package com.app.timetable.service.impl;

import com.app.timetable.model.entity.StorePicture;
import com.app.timetable.mapper.StorePictureMapper;
import com.app.timetable.service.IStorePictureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Service
public class StorePictureServiceImpl extends ServiceImpl<StorePictureMapper, StorePicture> implements IStorePictureService {

    @Autowired
    private StorePictureMapper storePictureMapper;

    @Override
    public IPage<StorePicture> selectByPage(int pageNum, int pageSize) {
        Page<StorePicture> page = new Page<>(pageNum,pageSize);
        QueryWrapper<StorePicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return storePictureMapper.selectPage(page,queryWrapper);
    }
}
