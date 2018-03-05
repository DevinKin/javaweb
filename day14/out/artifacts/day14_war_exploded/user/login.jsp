<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-26
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录</h1>
<form action="<c:url value='/LoginServlet'/>" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/>
    <b style="color: red; font-weight: 500;">${msg}</b>
    <br/>
    密码：<input type="password" name="password" value="${user.password}"/>
    <br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
