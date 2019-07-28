package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Data
public class Course extends BaseModel {

    private static final long serialVersionUID = 1L;

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
     * 课程状态：0-待发布，1-已发布，2-下架
     */
    private Integer status;

    /**
     * 课程分类ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    /**
     * 图片url
     */
    private String imgUrl;
}
