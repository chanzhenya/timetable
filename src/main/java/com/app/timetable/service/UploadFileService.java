package com.app.timetable.service;

import com.app.timetable.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

/**
 * judithchen
 * 2019/4/6
 * Description：文件上传
 **/
public interface UploadFileService {

    Picture uploadFile(MultipartFile multipartFile);

    void delete(String imgUrl);
}
