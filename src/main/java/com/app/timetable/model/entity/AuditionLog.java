package com.app.timetable.model.entity;

import com.app.timetable.common.model.BaseModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author judithchen
 * @since 2019-04-16
 */
@Data
public class AuditionLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Long courseId;

    private Long teacherId;

    private Long studentId;
}
