package com.app.timetable.controller;


import com.app.timetable.entity.SysConfig;
import com.app.timetable.service.ISysConfigService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private ISysConfigService configService;

    /**
     * 设置可请假次数
     * @param number
     * @return
     */
    @PostMapping("/config")
    public ResultVo config(@RequestParam("number") Integer number) {
        configService.config(number);
        return ResultVoUtil.success("设置成功");
    }

    @GetMapping("/configInfo")
    public ResultVo getConfig() {
        SysConfig sysConfig = configService.getConfig();
        return ResultVoUtil.success(sysConfig);
    }
}

