package com.app.timetable.service.impl;

import com.app.timetable.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    ISysUserService sysUserService;

    @Test
    public void saveSysUser() {
        String openId = "o-W4K40OjKat7zyBgJHnpf0gEt5A";
        sysUserService.saveSysUser(openId);
    }
}