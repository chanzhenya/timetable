package com.app.timetable.controller;


import com.app.timetable.model.entity.Picture;
import com.app.timetable.model.entity.StorePicture;
import com.app.timetable.service.IStorePictureService;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/storePicture")
public class StorePictureController {

    @Autowired
    private IStorePictureService pictureService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 门店图片上传
     * @param images 多张图片
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("images")MultipartFile[] images) {
        List<StorePicture> pictureList = new ArrayList<>();
        for(MultipartFile img:images) {
            Picture p = uploadFileService.uploadFile(img);
            StorePicture picture = new StorePicture();
            picture.setImgUrl(p.getImgUrl());
            pictureList.add(picture);
        }
        pictureService.saveBatch(pictureList);
        return ResultVoUtil.success("上传成功");
    }

    /**
     * 获取门店图片
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize) {
        IPage<StorePicture> storePictureIPage = pictureService.selectByPage(pageNum,pageSize);
        return ResultVoUtil.success(storePictureIPage);
    }

    /**
     * 根据图片ID删除图片
     * @param pictureId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("pictureId") String pictureId) {
        StorePicture storePicture = pictureService.getById(pictureId);
        pictureService.removeById(pictureId);
        uploadFileService.delete(storePicture.getImgUrl());
        return ResultVoUtil.success("删除成功");
    }
}

