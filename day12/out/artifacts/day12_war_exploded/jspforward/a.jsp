<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-22
  Time: 下午9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>a.jsp</h1>
<%--动态包含--%>
<jsp:forward page="b.jsp">
    <jsp:param name="username" value="zhangsan"/>
    <jsp:param name="password" value="123"/>
</jsp:forward>

</body>
</html>
