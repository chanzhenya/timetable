package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-15
 */
@Data
public class StorePicture extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 图片地址链接
     */
    private String imgUrl;
}
