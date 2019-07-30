package com.app.timetable.service;

import com.app.timetable.model.entity.Tag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface ITagService extends IService<Tag> {

    IPage<Tag> list(Map<String,Object> params);
}
