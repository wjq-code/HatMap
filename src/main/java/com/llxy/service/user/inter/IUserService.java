package com.llxy.service.user.inter;

import com.llxy.model.User;

import java.util.List;

/**
 * ������: IUserServer
 * ������:
 *
 * @author �佨ǫ
 * @Time 2019/1/7 18:20
 */
public interface IUserService {
    void add(User user);
    void delete(String userName);
    void upDate(User user);
    User load(String userName);
    List<User> loadList();

    User login(String userName, String passWord);

}

