package com.app.timetable.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String imgUrl;

    private String imgPath;


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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Picture{" +
        "id=" + id +
        ", imgUrl=" + imgUrl +
        ", imgPath=" + imgPath +
        "}";
    }
}
