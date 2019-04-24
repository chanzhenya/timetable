package com.app.timetable.service;

import com.app.timetable.entity.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
public interface ISysConfigService extends IService<SysConfig> {

    void config(int number) throws Exception;

    SysConfig getConfig() throws Exception;
}
