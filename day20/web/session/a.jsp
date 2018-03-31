<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/3/30
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>向session中保存数据</h1>
<%
    session.setAttribute("xxx","session1");
    session.setAttribute("user2", new cn.devinkin.cstm.domain.User2("haha","hehe"));
%>

</body>
</html>
