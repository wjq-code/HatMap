package com.llxy.service.student.impl;

import com.llxy.dao.impl.StudentDaoImpl;
import com.llxy.dao.impl.UserDaoImpl;
import com.llxy.dao.inter.IStudentDao;
import com.llxy.dao.inter.IUserDao;
import com.llxy.execption.SSCExecption;
import com.llxy.model.Student;
import com.llxy.model.User;
import com.llxy.service.student.inter.IStudentService;

import java.util.List;

/**
 * 类名称: StudentServiceImpl
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/8 10:25
 */
public class StudentServiceImpl implements IStudentService {
    IStudentDao studentDao = new StudentDaoImpl();
    IUserDao userDao = new UserDaoImpl();

    @Override
    public void add(Student student) {
        String studentNo = student.getStudentNo();
        int age = student.getAge();
        List<User> users = userDao.loadList();
        for (User user : users) {
            if (user.getUserName().equals(studentNo)) {
                throw new SSCExecption("用户已存在");
            }
        }
        User user = new User(studentNo, "111", "xs");
        userDao.add(user);

        User user1 = userDao.load(studentNo);
        int userId = user1.getId();

        student.setUserId(userId);
        studentDao.add(student);
    }

    @Override
    public void delete(String studentNo) {
        studentDao.delete(studentNo);

        userDao.delete(studentNo);
    }

    @Override
    public void upDate(Student student) {
        studentDao.upDate(student);
    }

    @Override
    public Student load(String studentNo) {
        return studentDao.load(studentNo);
    }

    @Override
    public List<Student> loadList() {
        return studentDao.loadList();
    }

    @Override
    public int loadByUserId(int userId) {
        return studentDao.loadByUserId(userId);
    }


}
