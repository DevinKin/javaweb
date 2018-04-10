<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/5
  Time: 10:57
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
${requestScope.msg}
<form action="<c:url value='/LoginServlet'/>" method="post">
    用户名：<input type="text" name="username"/>
    <input type="submit" value="登录"/>
</form>

</body>
</html>
