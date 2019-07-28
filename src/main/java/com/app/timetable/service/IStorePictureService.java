package com.app.timetable.service;

import com.app.timetable.model.entity.StorePicture;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
public interface IStorePictureService extends IService<StorePicture> {

    IPage<StorePicture> selectByPage(int pageNum, int pageSize);
}
