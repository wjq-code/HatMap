package com.llxy.dao.inter;



import com.llxy.model.User;

import java.util.List;

/**
 * 类名称: IUserDao
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:09
 */
public interface IUserDao {
    void add(User user);
    void delete(String userName);
    void upDate(User user);
    User load(String userName);
    List<User> loadList();


}
