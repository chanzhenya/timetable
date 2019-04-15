package com.app.timetable.entity;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
