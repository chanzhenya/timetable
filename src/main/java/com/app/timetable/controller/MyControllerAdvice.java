package com.app.timetable.controller;

import com.app.timetable.exception.MyException;
import com.app.timetable.utils.ResultVoUtil;
import com.app.timetable.vo.ResultVo;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Judith
 * @date 2019/4/2 14:16
 * @description controller增强器
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    public void initBinder(WebDataBinder binder) {

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    public void addAttributes(Model model) {

    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo errorHandler(Exception ex) {
        ex.printStackTrace();
        return ResultVoUtil.error(ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param me
     * @return
     */
    public ResultVo myErrorHandler(MyException me) {
        return ResultVoUtil.error(me.getMsg());
    }
}
