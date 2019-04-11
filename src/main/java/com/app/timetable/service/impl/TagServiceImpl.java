package com.app.timetable.service.impl;

import com.app.timetable.entity.Tag;
import com.app.timetable.mapper.TagMapper;
import com.app.timetable.service.ITagService;
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
 * @since 2019-04-01
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public IPage<Tag> selectPage(int pageNum, int pageSize) throws Exception {
        Page<Tag> page = new Page<>(pageNum,pageSize);
        return tagMapper.selectPage(page,new QueryWrapper<>());
    }
}
