package com.llxy.model;

/**
 * 类名称: Student
 * 类描述:
 *
 * @author 武建谦
 * @Time 2019/1/7 17:05
 */
public class Student {
    private int id;
    private String studentNo;
    private String name;
    private int age;
    private int userId;

    public Student() {
    }

    public Student(int id, String studentNo, String name, int age, int userId) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
        this.userId = userId;
    }

    public Student(int id, String studentNo, String name, int age) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
