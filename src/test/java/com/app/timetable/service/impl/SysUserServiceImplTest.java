package com.app.timetable.service.impl;

import com.app.timetable.entity.SysUser;
import com.app.timetable.service.ISysUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * judithchen
 * 2019-07-19
 * Description：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void selectByOpenId() {
        SysUser sysUser = sysUserService.selectByOpenId("o-W4K44VC8rGPGb3rxHI4EXodJ10");
        Assert.assertNotNull(sysUser);
    }
}