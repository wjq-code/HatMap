package com.llxy.model;

/**
 * 类名称: User
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:05
 */
public class User {
    private int id;
    private String userName;
    private String passWord;
    private String type;

    public User() {
    }

    public User(String userName, String passWord, String type) {
        this.userName = userName;
        this.passWord = passWord;
        this.type = type;
    }

    public User(int id, String userName, String passWord, String type) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
