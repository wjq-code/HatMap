package com.llxy.service.teacher.inter;

import com.llxy.model.Teacher;

import java.util.List;

/**
 * ������: IUserServer
 * ������:
 *
 * @author �佨ǫ
 * @Time 2019/1/7 18:20
 */
public interface ITeacherService {
    void add(Teacher teacher);
    void delete(String teacherNo);
    void upDate(Teacher teacher);
    Teacher load(String teacherNo);
    List<Teacher> loadList();

}

