package com.llxy.service.student.inter;

import com.llxy.model.Student;

import java.util.List;

/**
 * ÀàÃû³Æ: StudentSeriveImpl
 * ÀàÃèÊö:
 */
public interface IStudentService {
    void add(Student student);
    void delete(String studentNo);
    void upDate(Student student);
    Student load(String studentNo);
    List<Student> loadList();
    int loadByUserId(int userId);
}
