package com.app.timetable.dto;

import com.app.timetable.entity.SysUser;
import lombok.Data;

/**
 * judithchen
 * 2019/4/11
 * Description：
 **/
@Data
public class TeacherDTO {

    /**
     * 教师ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 教师综合评分
     */
    private Integer score;

    /**
     * 用户信息
     */
    private SysUser user;
}
