package com.app.timetable.service.impl;

import com.app.timetable.entity.Student;
import com.app.timetable.entity.SysUser;
import com.app.timetable.entity.Teacher;
import com.app.timetable.enums.UserType;
import com.app.timetable.mapper.SysUserMapper;
import com.app.timetable.service.IStudentService;
import com.app.timetable.service.ISysUserService;
import com.app.timetable.service.ITeacherService;
import com.app.timetable.utils.ClassObjectUtils;
import com.app.timetable.utils.CommonContent;
import com.app.timetable.utils.CookieUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper  userMapper;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IStudentService studentService;

    @Override
    public void register(SysUser sysUser, HttpServletResponse response) throws Exception {
        userMapper.insert(sysUser);
        if(UserType.TEACHER.getCode().equals(sysUser.getUserType())) {
            saveTeacher(sysUser);
        } else if(UserType.STUDENT.getCode().equals(sysUser.getUserType())) {
            saveStudent(sysUser);
        }
        String token = CookieUtil.getUUToken();
        CookieUtil.setTokenCookie(CommonContent.TOKEN_KEY,token, (int) CommonContent.LOGIN_EXPIRE_TIME, response);
    }

    @Override
    public IPage<SysUser> selectPage(int pageNum, int pageSize, SysUser user) throws Exception {
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        return userMapper.selectPage(page,user);
    }

    @Override
    public SysUser getUserDetail(String userId) throws Exception {
        SysUser sysUser = userMapper.selectById(userId);
        return sysUser;
    }

    private void saveTeacher(SysUser sysUser) {
        Teacher teacher = new Teacher();
        teacher.setId(ClassObjectUtils.getUUID());
        teacher.setUserId(sysUser.getId());
        teacher.setCreateTime(LocalDateTime.now());
        teacherService.save(teacher);
    }

    private void saveStudent(SysUser sysUser) {
        Student student = new Student();
        student.setId(ClassObjectUtils.getUUID());
        student.setUserId(sysUser.getId());
        student.setCreateTime(LocalDateTime.now());
        studentService.save(student);
    }
}
