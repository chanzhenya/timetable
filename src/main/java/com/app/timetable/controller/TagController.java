package com.app.timetable.controller;


import com.app.timetable.annotation.UserLoginToken;
import com.app.timetable.entity.Tag;
import com.app.timetable.service.ITagService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagName") String tagName) {
        try {
            Tag tag = new Tag(ClassObjectUtils.getUUID(),tagName);
            tagService.save(tag);
            return ResultVoUtil.success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResultVo edit(@RequestParam("tagId") String tagId, @RequestParam("tagName") String tagName) {
        try {
            Tag tag = new Tag(tagId,tagName);
            tagService.updateById(tag);
            return ResultVoUtil.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("tagId") String tagId) {
        try {
            tagService.removeById(tagId);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }

    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            IPage<Tag> tagIPage = tagService.selectPage(pageNum,pageSize);
            return ResultVoUtil.success(tagIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(e.getMessage());
        }
    }
}

