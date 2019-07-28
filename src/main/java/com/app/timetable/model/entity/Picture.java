package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
@Data
public class Picture extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String imgUrl;

    private String imgPath;
}
