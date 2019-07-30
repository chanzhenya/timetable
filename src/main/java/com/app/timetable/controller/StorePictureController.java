package com.app.timetable.controller;


import com.app.timetable.model.entity.Picture;
import com.app.timetable.model.entity.StorePicture;
import com.app.timetable.service.IPictureService;
import com.app.timetable.service.IStorePictureService;
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
import java.util.Map;

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
    private IStorePictureService storePictureService;

    @Autowired
    private IPictureService pictureService;

    /**
     * 门店图片上传
     * @param images 多张图片
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("images")MultipartFile[] images) {
        List<StorePicture> pictureList = new ArrayList<>();
        for(MultipartFile img:images) {
            Picture p = pictureService.uploadFile(img);
            StorePicture picture = new StorePicture();
            picture.setImgUrl(p.getImgUrl());
            pictureList.add(picture);
        }
        storePictureService.saveBatch(pictureList);
        return ResultVoUtil.success("上传成功");
    }

    /**
     * 获取门店图片
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        IPage<StorePicture> storePictureIPage = storePictureService.selectByPage(params);
        return ResultVoUtil.success(storePictureIPage);
    }

    /**
     * 根据图片ID删除图片
     * @param pictureId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("pictureId") String pictureId) {
        StorePicture storePicture = storePictureService.getById(pictureId);
        storePictureService.removeById(pictureId);
        pictureService.delete(storePicture.getImgUrl());
        return ResultVoUtil.success("删除成功");
    }
}

