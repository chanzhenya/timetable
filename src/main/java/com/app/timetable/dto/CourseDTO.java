package com.app.timetable.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Judith
 * @date 2019/4/17 19:52
 * @description
 */
@Data
public class CourseDTO {

    /**
     * 课程id
     */
    private String id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程描述
     */
    private String descreption;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 课时
     */
    private Integer period;

    /**
     * 教师id
     */
    private String teacherId;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 课程状态：0-待发布，1-已发布，2-下架
     */
    private Integer status;

    /**
     * 课程分类ID
     */
    private String tagId;

    /**
     * 图片url
     */
    private String imgUrl;

    private LocalDateTime createTime;
}
