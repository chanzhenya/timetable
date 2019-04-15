package com.app.timetable.controller;


import com.app.timetable.service.IStudentTimtableService;
import com.app.timetable.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/studentTimtable")
public class StudentTimtableController {

    @Autowired
    private IStudentTimtableService studentTimtableService;
}

