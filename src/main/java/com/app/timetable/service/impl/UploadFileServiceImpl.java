package com.app.timetable.service.impl;

import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.CommonContent;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Judith
 * @date 2019/4/2 19:38
 * @description
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        if(file.isEmpty()) {
            throw new NullPointerException("文件为空");
        }

        //获取文件名后缀
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".")+1);

        //文件重命名
        String fileName = UUID.randomUUID() +"." +  prefix;

        //创建文件路径
        File dest = new File(CommonContent.FILE_PATH+fileName);
        //检查目录是否存在
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //将文件写到服务器上指定的文件中
        file.transferTo(dest);
        return CommonContent.FILE_PATH+fileName;
    }
}
