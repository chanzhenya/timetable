package com.app.timetable.service.impl;

import com.app.timetable.model.entity.Picture;
import com.app.timetable.mapper.PictureMapper;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.CommonContent;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Judith
 * @date 2019/4/2 19:38
 * @description
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public Picture uploadFile(MultipartFile multipartFile) {
        if(multipartFile == null) {
            return new Picture();
        }

        Picture picture = new Picture();
        picture.setId(ClassObjectUtils.getUUID());

        //获取文件名后缀
        String suffix = multipartFile.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".")+1);

        //重命名
        String filename = picture.getId()+"."+prefix;

        //创建文件路径
        File dest = new File(CommonContent.FILE_PATH+filename);

        //检查目录是否存在
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        //将文件写到服务器上指定的文件中
        try {
            multipartFile.transferTo(dest);
            picture.setImgPath(CommonContent.FILE_PATH + filename);
            picture.setImgUrl(CommonContent.IMAGE_URL + filename);
            pictureMapper.insert(picture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;
    }

    @Override
    public void delete(String imgUrl) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("img_url",imgUrl);
        List<Picture> pictures = pictureMapper.selectList(queryWrapper);
        if(!pictures.isEmpty()) {
            Picture picture = pictures.get(0);
            pictureMapper.deleteById(picture.getId());
            File file = new File(picture.getImgPath());
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }
}
