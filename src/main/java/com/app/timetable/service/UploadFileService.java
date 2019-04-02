package com.app.timetable.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Judith
 * @date 2019/4/2 19:37
 * @description 文件上传service
 */
public interface UploadFileService {

    /**
     * 上传图片
     * @param file
     * @return 文件保存路径
     */
    public String uploadFile(MultipartFile file) throws IOException;
}
