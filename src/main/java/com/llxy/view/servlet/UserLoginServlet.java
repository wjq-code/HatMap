package com.llxy.view.servlet;

import com.llxy.model.User;
import com.llxy.service.user.impl.UserServiceImpl;
import com.llxy.service.user.inter.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 类名称: UserLoginServlet
 * 类描述:
 * @author 王倩
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    IUserService ius=  new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入servlet");
//        //设置请求响应编码
//        req.setCharacterEncoding("UTF-8");
//        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        //获取到表单中的数据，并封装javabean
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String type = req.getParameter("type");

        //调用业务层
        User user = ius.login(username, password);
        if (user != null) {
            resp.getWriter().write("登录成功");
            resp.sendRedirect(this.getServletContext().getContextPath()+"/ok.jsp");
        }else {
            resp.getWriter().write("登录失败,请检查用户名和密码");
        }

    }
}
