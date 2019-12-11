<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta charset="utf-8">
<html>
<head>
    <title>登录界面</title>
    <link resource="stylesheet" type="text/css" href="/css/login/login.css">
</head>
<body style="background: #9e599a">
<form method="post" action="${pageContext.servletContext.contextPath}/UserLoginServlet"><br/>
    <div class="user">用户名:&nbsp;<input type="text" name="username" placeholder="请输入用户名"/>
        <p>密&nbsp;&nbsp;&nbsp;码:&nbsp;<input type="password" name="password" placeholder="请输入密码"/></p>
        <td><select name="type" width="40px">
            <option value="student">学生</option>
            <option value="teacher">教师</option>
        </select></td>
        <p><a href="register.jsp"><input type="button" class="register" value="注册"/>
        </a></span><input class="login" type="submit" value="登录"/></p>
    </div>
</form>
</body>
</html>