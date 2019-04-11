package com.app.timetable.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * judithchen
 * 2019/4/6
 * Description：文件上传
 **/
public interface UploadFileService {

    String uploadFile(MultipartFile multipartFile) throws Exception;
}
