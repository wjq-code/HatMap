package com.llxy.dao.inter;

import com.llxy.model.Student;

import java.util.List;

/**
 * ������: IStudentDao
 * ������:
 *
 * @author �佨ǫ
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
