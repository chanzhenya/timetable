package com.app.timetable.controller;


import com.app.timetable.model.dto.TeacherEvaluationDTO;
import com.app.timetable.model.entity.TeacherEvaluation;
import com.app.timetable.service.ITeacherEvaluationService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.model.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
@RequestMapping("/teacherEvaluation")
public class TeacherEvaluationController {

    @Autowired
    private ITeacherEvaluationService teacherEvaluationService;

    /**
     * 添加教师评价
     * @param studentId 学生id
     * @param teacherId 教师id
     * @param score 评分
     * @param content 评价内容
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestParam("studentId") Long studentId, @RequestParam("teacherId") Long teacherId,
                        @RequestParam(value = "score", required = false) double score, @RequestParam(value = "content", required = false) String content) {
        TeacherEvaluation evaluation = new TeacherEvaluation();
        evaluation.setStudentId(studentId);
        evaluation.setTeacherId(teacherId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        teacherEvaluationService.saveTeacherEvaluation(evaluation);
        return ResultVoUtil.success("提交成功");
    }

    /**
     * 教师评论列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam Map<String,Object> params) {
        return ResultVoUtil.success(teacherEvaluationService.selectByPage(params));
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("id") String id) {
        teacherEvaluationService.removeById(id);
        return ResultVoUtil.success("删除成功");
    }
}

