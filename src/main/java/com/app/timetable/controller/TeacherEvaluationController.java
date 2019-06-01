package com.app.timetable.controller;


import com.app.timetable.dto.TeacherEvaluationDTO;
import com.app.timetable.entity.TeacherEvaluation;
import com.app.timetable.service.ITeacherEvaluationService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
    public ResultVo add(@RequestParam("studentId") String studentId, @RequestParam("teacherId") String teacherId,
                        @RequestParam(value = "score", required = false) double score, @RequestParam(value = "content", required = false) String content) {
        TeacherEvaluation evaluation = new TeacherEvaluation();
        evaluation.setId(ClassObjectUtils.getUUID());
        evaluation.setStudentId(studentId);
        evaluation.setTeacherId(teacherId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        evaluation.setCreateTime(LocalDateTime.now());
        teacherEvaluationService.insert(evaluation);
        return ResultVoUtil.success("提交成功");
    }

    /**
     * 教师评论列表
     * @param teacherId
     * @param studentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public ResultVo list(@RequestParam(value = "teacherId", required = false) String teacherId,
                         @RequestParam(value = "studentId", required = false) String studentId,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "8000") int pageSize) {
        TeacherEvaluation evaluation = new TeacherEvaluation();
        evaluation.setTeacherId(teacherId);
        evaluation.setStudentId(studentId);
        IPage<TeacherEvaluationDTO> evaluationDTOIPage = teacherEvaluationService.selectByPage(pageNum,pageSize,evaluation);
        return ResultVoUtil.success(evaluationDTOIPage);
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

