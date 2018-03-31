<%@ page import="cn.devinkin.cstm.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/3/30
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
User user = new User();
session.setAttribute("user",user);
%>
</body>
</html>
