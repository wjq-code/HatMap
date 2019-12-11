package com.llxy.dao.impl;

import com.llxy.dao.inter.ITeacherDao;
import com.llxy.model.Teacher;
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
public class TeacherDaoImpl implements ITeacherDao {
    JDBCUtils jdbc = JDBCUtils.getInstance();

    @Override
    public void add(Teacher teacher) {
        String sql = "insert into t_teacher(teacher_no,name,type,user_id) values(?,?,?,?)";
        String teacherNo = teacher.getTeacherNo();
        String name = teacher.getName();
        String type = teacher.getType();
        int userId = teacher.getUserId();
        Object[] param = new Object[]{teacherNo, name, type, userId};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("教师插入成功");
        }
    }

    @Override
    public void delete(String teacherNo) {
        String sql = "delete from t_teacher where teacher_no=?";
        Object[] param = new Object[]{teacherNo};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("教师删除成功");
        }
    }

    @Override
    public void upDate(Teacher teacher) {
        String sql = "update t_teacher set name=? where id = ?";
        String teacherName = teacher.getName();
        int id = teacher.getId();
        Object[] param = new Object[]{teacherName, id};
        int i = jdbc.executorUpdate(sql, param);
        if (i == 1) {
            System.out.println("教师修改成功");
        }
    }

    @Override
    public Teacher load(final String teacherNo) {
        final Teacher teacher = new Teacher();
        String sql = "select id,teacher_no,name,type,user_id from t_teacher where teacher_no=?";
        Object[] param = new Object[]{teacherNo};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String teacherNo = rs.getString("teacher_no");
                        String name = rs.getString("name");
                        String type = rs.getString("type");
                        int userId = rs.getInt("user_id");
                        teacher.setId(id);
                        teacher.setTeacherNo(teacherNo);
                        teacher.setName(name);
                        teacher.setType(type);
                        teacher.setUserId(userId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return teacher;
    }

    @Override
    public List<Teacher> loadList() {
        final List<Teacher> teachers = new ArrayList<>();
        String sql = "select id,teacher_no,name,type ,user_id from t_teacher";
        Object[] param = new Object[]{};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String teacherNo = rs.getString("teacher_no");
                        String name = rs.getString("name");
                        String type = rs.getString("type");
                        int userId = rs.getInt("user_id");
                        Teacher teacher = new Teacher(id, teacherNo, name, type, userId);
                        teachers.add(teacher);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return teachers;
    }

    @Override
    public int loadByUserId(int userId) {
        final int[] teacherId = {0};
        String sql = "SELECT\n" +
                "\tid\n" +
                "FROM\n" +
                "\tt_teacher\n" +
                "WHERE\n" +
                "\tuser_id = ? ";
        Object[] param = new Object[]{userId};
        jdbc.executorQurey(sql, param, new JDBCUtils.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if (rs.next()) {
                        teacherId[0] = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return teacherId[0];
    }
}
