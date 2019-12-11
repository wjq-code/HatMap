package com.llxy.service.user.inter;

import com.llxy.model.User;

import java.util.List;

/**
 * 类名称: IUserServer
 * 类描述:
 *
 * @author 武建谦
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

