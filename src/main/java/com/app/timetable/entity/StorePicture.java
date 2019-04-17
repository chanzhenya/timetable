package com.app.timetable.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
public class StorePicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    private String id;

    /**
     * 图片地址链接
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StorePicture{" +
        "id=" + id +
        ", imgUrl=" + imgUrl +
        "}";
    }
}
