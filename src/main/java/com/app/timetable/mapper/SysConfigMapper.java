package com.app.timetable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    void deleteAll();

    List<SysConfig> selectAll();
}
