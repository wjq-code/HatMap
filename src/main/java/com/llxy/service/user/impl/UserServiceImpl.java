package com.llxy.service.user.impl;


import com.llxy.dao.impl.UserDaoImpl;
import com.llxy.dao.inter.IUserDao;
import com.llxy.execption.SSCExecption;
import com.llxy.model.User;
import com.llxy.service.user.inter.IUserService;
import com.llxy.util.MD5Util;

import java.util.List;

/**
 * 类名称: UserServerImpl
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 18:34
 */
public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public void add(User user) {

    }

    @Override
    public void delete(String userName) {

    }

    @Override
    public void upDate(User user) {

    }

    @Override
    public User load(String userName) {
        return null;
    }

    @Override
    public List<User> loadList() {
        return null;
    }

    @Override
    public User login(String userName,String passWord) {
        System.out.println("userName: " + userName + "PassWord:" + passWord);
        User user = userDao.load(userName);
        System.out.println("load: " + user);
        if (user== null) {
            throw new SSCExecption("用户不存在");
        }
        if (!user.getPassWord().equals(MD5Util.md5Password(passWord))){
            throw new SSCExecption("密码错误");
        }
        return user;
    }
}
