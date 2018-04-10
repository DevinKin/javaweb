<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/9
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value='/WebServlet?username=张三'/>">点击这里</a>
<form action="<c:url value='/WebServlet'/>" method="post">
    <input type="text" name="username" value="李四"/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
