package com.llxy.model;

/**
 * 类名称: Teacher
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:05
 */
public class Teacher {
    private int id;
    private String teacherNo;
    private String name;
    private String type;
    private int userId;

    public Teacher() {
    }

    public Teacher(int id, String teacherNo, String name, String type, int userId) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public Teacher(int id, String teacherNo, String name, String type) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNo='" + teacherNo + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
