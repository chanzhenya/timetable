package com.app.timetable.service;

import com.app.timetable.dto.StudentTimetableDTO;
import com.app.timetable.entity.StudentTimtable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author judithchen
 * @since 2019-04-01
 */
public interface IStudentTimtableService extends IService<StudentTimtable> {

    void add(List<StudentTimtable> timtableList) throws Exception;

    IPage<StudentTimetableDTO> selectBuPage(int pageNum, int pageSize, StudentTimtable timtable);

    void signIn(StudentTimtable studentTimtable, String teacherTimetableId) throws Exception;

    void publishHomework(String id, String homework) throws Exception;

    /**
     * 学生请假和旷课处理
     * @param studentTimtable
     * @return
     * @throws Exception
     */
    int leaveAndTruancy(StudentTimtable studentTimtable) throws Exception;
}
