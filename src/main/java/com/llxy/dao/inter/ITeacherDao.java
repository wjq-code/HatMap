package com.llxy.dao.inter;


import com.llxy.model.Teacher;

import java.util.List;

/**
 * ������: IUserDao
 * ������:
 *
 * @author �佨ǫ
 * @Time 2019/1/7 17:09
 */
public interface ITeacherDao {
    void add(Teacher teacher);
    void delete(String teacherNo);
    void upDate(Teacher teacher);
    Teacher load(String teacherNo);
    List<Teacher> loadList();

    int loadByUserId(int userId);

}
