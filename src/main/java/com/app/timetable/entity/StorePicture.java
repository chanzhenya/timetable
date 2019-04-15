package com.app.timetable.entity;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return "StorePicture{" +
        "id=" + id +
        ", imgUrl=" + imgUrl +
        "}";
    }
}
