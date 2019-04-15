package com.app.timetable.controller;


import com.app.timetable.entity.StorePicture;
import com.app.timetable.service.IStorePictureService;
import com.app.timetable.service.UploadFileService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        try {
            List<StorePicture> pictureList = new ArrayList<>();
            for(MultipartFile img:images) {
                String path = uploadFileService.uploadFile(img);
                StorePicture picture = new StorePicture();
                picture.setId(ClassObjectUtils.getUUID());
                picture.setImgUrl(path);
                pictureList.add(picture);
            }
            pictureService.saveBatch(pictureList);
            return ResultVoUtil.success("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 获取门店图片
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            Page<StorePicture> page = new Page<>(pageNum,pageSize);
            IPage<StorePicture> storePictureIPage = pictureService.page(page);
            return ResultVoUtil.success(storePictureIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    /**
     * 根据图片ID删除图片
     * @param pictureId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("pictureId") String pictureId) {
        try {
            pictureService.removeById(pictureId);
            return ResultVoUtil.success("删除成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultVoUtil.error(ex.getMessage());
        }
    }
}

