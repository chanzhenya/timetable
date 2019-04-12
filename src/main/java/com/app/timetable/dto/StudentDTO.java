package com.app.timetable.dto;

import com.app.timetable.entity.SysUser;
import lombok.Data;

/**
 * judithchen
 * 2019/4/11
 * Description：
 **/
@Data
public class StudentDTO {

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 旷课次数
     */
    private Integer truancyNum;

    /**
     * 请假次数
     */
    private Integer leaveNum;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 用户信息
     */
    private SysUser sysUser;
}
