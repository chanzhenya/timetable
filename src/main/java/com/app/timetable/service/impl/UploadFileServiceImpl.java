package com.app.timetable.service.impl;

import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.CommonContent;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author Judith
 * @date 2019/4/2 19:38
 * @description
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        if(multipartFile != null) {
            throw new NullPointerException("文件为空");
        }

        //获取文件名后缀
        String suffix = multipartFile.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".")+1);

        //重命名
        String filename = UUID.randomUUID()+"."+prefix;

        //创建文件路径
        File dest = new File(CommonContent.FILE_PATH+filename);

        //检查目录是否存在
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        //将文件写到服务器上指定的文件中
        multipartFile.transferTo(dest);
        return CommonContent.FILE_PATH+filename;
    }
}
