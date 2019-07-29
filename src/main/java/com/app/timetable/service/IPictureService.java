package com.app.timetable.service;

import com.app.timetable.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Judith
 * @date 2019/7/29 15:52
 * @description
 */
public interface IPictureService extends IService<Picture> {

    Picture uploadFile(MultipartFile multipartFile);
    void delete(String imgUrl);
}
