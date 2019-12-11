package com.llxy.dao.impl;

import com.llxy.dao.inter.IUserDao;
import com.llxy.model.User;
import com.llxy.util.JDBCUtils;
import com.llxy.util.MD5Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名称: UserDaoImpl
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:13
 */
public class UserDaoImpl implements IUserDao {
    JDBCUtils jdbc = JDBCUtils.getInstance();

    @Override
    public void add(User user) {
        String sql = "insert into t_user(username,password,type) values(?,?,?)";
        String userName = user.getUserName();
        String passWord = MD5Util.md5Password(user.getPassWord());
        String type = user.getType();
        Object[] param = new Object[]{userName, passWord, type};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("用户插入成功");
        }
    }

    @Override
    public void delete(String userName) {
        String sql = "delete from t_user where userName=?";
        Object[] param = new Object[]{userName};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("用户删除成功");
        }
    }

    @Override
    public void upDate(User user) {
        String sql = "update t_user set username=? , password=?";
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        Object[] param = new Object[]{userName, passWord};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("用户修改成功");
        }
    }

    @Override
    public User load(String userName) {
        final User[] user = {null};
        String sql = "select id,username,password,type from t_user where username=?";
        Object[] param = new Object[]{userName};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if (rs.next()) {
                        user[0] = new User();
                        int id = rs.getInt("id");
                        String userName = rs.getString("username");
                        String password = rs.getString("password");
                        String type = rs.getString("type");
                        user[0].setId(id);
                        user[0].setUserName(userName);
                        user[0].setPassWord(password);
                        user[0].setType(type);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return user[0];
    }

    @Override
    public List<User> loadList() {
        final List<User> users = new ArrayList<>();
        String sql = "select id,username,password,type from t_user";
        Object[] param = new Object[]{};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String userName = rs.getString("username");
                        String password = rs.getString("password");
                        String type = rs.getString("type");
                        User user = new User(id, userName, password, type);
                        users.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return users;
    }
}
