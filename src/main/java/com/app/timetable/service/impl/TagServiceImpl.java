package com.app.timetable.service.impl;

import cn.hutool.core.map.MapUtil;
import com.app.timetable.common.model.BaseService;
import com.app.timetable.model.entity.Tag;
import com.app.timetable.mapper.TagMapper;
import com.app.timetable.service.ITagService;
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
 * @since 2019-04-01
 */
@Service
public class TagServiceImpl extends BaseService<TagMapper, Tag> implements ITagService {

    @Override
    public IPage<Tag> list(Map<String, Object> params) {
        Page<Tag> tagPage = initPage(params);
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        if(params.containsKey("tagName")) {
            tagQueryWrapper.like("name", MapUtil.getStr(params,"tagName"));
        }
        tagQueryWrapper.eq("enable",1);
        return page(tagPage,tagQueryWrapper);
    }
}
