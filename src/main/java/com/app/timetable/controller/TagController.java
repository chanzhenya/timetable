package com.app.timetable.controller;


import com.app.timetable.entity.Tag;
import com.app.timetable.service.ITagService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    /**
     * 新增标签
     * @param tagName
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("tagName") String tagName) {
        Tag tag = new Tag(ClassObjectUtils.getUUID(),tagName);
        tagService.save(tag);
        return ResultVoUtil.success(null);
    }

    /**
     * 更新标签
     * @param tagId
     * @param tagName
     * @return
     */
    @PostMapping("/update")
    public ResultVo update(@RequestParam("tagId") String tagId, @RequestParam("tagName") String tagName) {
        Tag tag = new Tag(tagId,tagName);
        tagService.updateById(tag);
        return ResultVoUtil.success(null);
    }

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("tagId") String tagId) {
        tagService.removeById(tagId);
        return ResultVoUtil.success(null);
    }

    /**
     * 获取标签列表
     * @return
     */
    @GetMapping("/list")
    public ResultVo list() {
        List<Tag> tagList = tagService.list();
        return ResultVoUtil.success(tagList);
    }
}

