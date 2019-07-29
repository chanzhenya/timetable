package com.app.timetable.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    /**
     * 课程分类名称
     */
    private String tagName;

    /**
     * 图片url
     */
    private String imgUrl;

    private LocalDateTime createTime;
}
