package com.llxy.service.teacher.impl;

import com.llxy.dao.impl.StudentDaoImpl;
import com.llxy.dao.impl.TeacherDaoImpl;
import com.llxy.dao.impl.UserDaoImpl;
import com.llxy.dao.inter.IStudentDao;
import com.llxy.dao.inter.ITeacherDao;
import com.llxy.dao.inter.IUserDao;
import com.llxy.execption.SSCExecption;
import com.llxy.model.Teacher;
import com.llxy.model.User;
import com.llxy.service.teacher.inter.ITeacherService;


import java.util.List;

/**
 * 类名称: UserServerImpl
 * 类描述:
 */
public class TeacherServiceImpl implements ITeacherService {
    ITeacherDao teacherDao = new TeacherDaoImpl();
    IUserDao userDao = new UserDaoImpl();
    IStudentDao studentDao = new StudentDaoImpl();
    @Override
    public void add(Teacher teacher) {
        String teacherNo = teacher.getTeacherNo();
        String type = teacher.getType();
        List<User> users = userDao.loadList();
        for (User user : users) {
            if (user.getUserName().equals(teacherNo)) {
                throw new SSCExecption("用户已存在");
            }
        }
        User user = new User(teacherNo, "111", type);
        userDao.add(user);

        User user1 = userDao.load(teacherNo);
        int userId = user1.getId();

        teacher.setUserId(userId);
        teacherDao.add(teacher);
    }

    @Override
    public void delete(String teacherNo) {
        teacherDao.delete(teacherNo);

        userDao.delete(teacherNo);
    }

    @Override
    public void upDate(Teacher teacher) {
        teacherDao.upDate(teacher);
    }

    @Override
    public Teacher load(String teacherNo) {
        return teacherDao.load(teacherNo);
    }

    @Override
    public List<Teacher> loadList() {
        return teacherDao.loadList();
    }

}
