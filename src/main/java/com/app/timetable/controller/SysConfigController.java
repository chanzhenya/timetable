package com.app.timetable.controller;


import com.app.timetable.entity.SysConfig;
import com.app.timetable.utils.BaseUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.apache.commons.lang3.math.NumberUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/sysConfig")
public class SysConfigController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 系统参数配置
     * @param params
     * @return
     * configType: LEAVE_NUMBER:可请假次数；BEFORE_COURSE_DUE_TIME: 课程到期前提醒的时间
     */
    @PostMapping("/config")
    public ResultVo config(@RequestParam Map<String,Object> params) {
        BaseUtils.checkParams(params,new String[]{"configType","number"});
        String configType = params.get("configType").toString();
        int number = Integer.parseInt(params.get("number").toString());
        RMap<String, Object> map = redissonClient.getMap("sys:config");
        map.put(configType, number);
        return ResultVoUtil.success("设置成功");
    }

    @GetMapping("/configInfo")
    public ResultVo getConfig() {
        RMap<String, Object> map = redissonClient.getMap("sys:config");
        return ResultVoUtil.success(map);
    }
}

