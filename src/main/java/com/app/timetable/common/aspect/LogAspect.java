package com.app.timetable.common.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.app.timetable.common.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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

    @Pointcut("execution(public * com.app.timetable.controller.*(..))")
    public void controllerPointCut(){};

    @Around("controllerPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startMs = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        String s = IdUtil.objectId();
        String reqParams = JSONUtil.toJsonPrettyStr(args);
        log.info("Method [{}.{}.{}] with params [{}] execute started", s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), reqParams);

        Object result = null;
        try{
            result = pjp.proceed();
        }catch (Throwable e){
            e.printStackTrace();
            if(e instanceof BusinessException){
                log.info("Method [{}.{}.{}] execute spend [{}]ms failure [{}] ", s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), System.currentTimeMillis() - startMs, JSONUtil.toJsonPrettyStr(e));
            }else{
                if(e.getCause()!=null){
                    log.info("Method [{}.{}.{}] execute spend [{}]ms failure [{}] ", s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), System.currentTimeMillis() - startMs, JSONUtil.toJsonPrettyStr(ExceptionUtil.unwrap(e.getCause())));
                }else{
                    log.info("Method [{}.{}.{}] execute spend [{}]ms failure [{}] ", s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), System.currentTimeMillis() - startMs, JSONUtil.toJsonPrettyStr(ExceptionUtil.unwrap(e)));
                }

            }
            throw e;
        }

        String returnStr = "";
        try{
            returnStr = JSONUtil.toJsonPrettyStr(result);
        }catch (Exception e){
            returnStr = result!=null?result.toString():"";
        }
        long interval = System.currentTimeMillis() - startMs;
        if(interval>3000){
            log.error("Method [{}.{}.{}] execute too slow spend [{}]ms return [{}]",s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), interval,returnStr);
        }else if(interval>1000){
            log.warn("Method [{}.{}.{}] execute too long spend [{}]ms return [{}]",s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), interval,returnStr);
        }else{
            log.info("Method [{}.{}.{}] execute spend [{}]ms return [{}]", s,pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), interval,returnStr);
        }
        return result;
    }
}
