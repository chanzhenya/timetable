package com.app.timetable.common.aspect;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Judith
 * @date 2019/7/31 18:47
 * @description
 */
@Aspect
public class LogAspect {

    Log log = LogFactory.get();

    @Pointcut("execution(public * com.app.timetable.controller.*.*(..))")
    public void controllerPointCut(){};
}
