package com.app.timetable.controller;

import com.app.timetable.common.utils.RobotAssert;
import com.app.timetable.model.entity.Tag;
import com.app.timetable.service.ITagService;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        Tag tag = new Tag();
        tag.setName(tagName);
        tagService.save(tag);
        return ResultVoUtil.success("新增成功");
    }

    @PostMapping("/edit")
    public ResultVo edit(@RequestParam("tagId") String tagId, @RequestParam("tagName") String tagName) {
        Tag tag = tagService.getById(tagId);
        RobotAssert.notNull(tag,"找不到想要的课程分类");
        tag.setName(tagName);
        tagService.updateById(tag);
        return ResultVoUtil.success("修改成功");
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("tagId") String tagId) {
        Tag tag = tagService.getById(tagId);
        RobotAssert.notNull(tag,"找不到想要的课程分类");
        tag.setEnable(0);
        tagService.updateById(tag);
        return ResultVoUtil.success("删除成功");
    }

    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(tagService.list(params));
    }
}

