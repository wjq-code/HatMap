package com.llxy.dao.impl;


import com.llxy.dao.inter.IStudentDao;
import com.llxy.model.Student;
import com.llxy.util.JDBCUtils;

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
public class StudentDaoImpl implements IStudentDao {
    JDBCUtils jdbc = JDBCUtils.getInstance();

    @Override
    public void add(Student student) {
        String sql = "insert into t_student(student_no,name,age,user_id) values(?,?,?,?)";
        String studentNo = student.getStudentNo();
        String name = student.getName();
        Integer type = student.getAge();
        int userId = student.getUserId();
        Object[] param = new Object[]{studentNo, name, type, userId};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("学生插入成功");
        }
    }

    @Override
    public void delete(String studentNo) {
        String sql = "delete from t_student where student_no=?";
        Object[] param = new Object[]{studentNo};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("学生删除成功");
        }
    }

    @Override
    public void upDate(Student student) {
        String sql = "update t_student set name=? where id=?";
        int id = student.getId();
        String studentName = student.getName();
        Object[] param = new Object[]{studentName, id};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("学生修改成功");
        }
    }

    @Override
    public Student load(final String studentNo) {
        final Student student = new Student();
        String sql = "select id,student_no,name,age,user_id from t_student where student_no=?";
        Object[] param = new Object[]{studentNo};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String studentNo = rs.getString("student_no");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int userId = rs.getInt("user_id");
                        student.setId(id);
                        student.setStudentNo(studentNo);
                        student.setName(name);
                        student.setAge(age);
                        student.setUserId(userId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return student;
    }

    @Override
    public List<Student> loadList() {
        final List<Student> students = new ArrayList<>();
        String sql = "select id,student_no,name,age ,user_id from t_student";
        Object[] param = new Object[]{};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String studentNo = rs.getString("student_no");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int userId = rs.getInt("user_id");
                        Student student = new Student(id, studentNo, name, age, userId);
                        students.add(student);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return students;
    }

    @Override
    public int loadByUserId(int userId) {
        final int[] studentId = {0};
        String sql = "SELECT\n" +
                "\tid\n" +
                "FROM\n" +
                "\tt_student\n" +
                "WHERE\n" +
                "\tuser_id = ? ";
        Object[] param = new Object[]{userId};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if (rs.next()) {
                        studentId[0] = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return studentId[0];
    }

}
