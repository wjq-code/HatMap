package com.llxy.dao.inter;

import com.llxy.model.Student;

import java.util.List;

/**
 * 类名称: IStudentDao
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:09
 */
public interface IStudentDao {
    void add(Student student);
    void delete(String studentNo);
    void upDate(Student student);
    Student load(String studentNo);
    List<Student> loadList();

    int loadByUserId(int userId);

}
