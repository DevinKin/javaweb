<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>你就是一个游客而已</h1>
<a href="<c:url value='/youke.jsp'/>">游客入口</a>
<a href="<c:url value='/users/u.jsp'/>">会员入口</a>
<a href="<c:url value='/admin/a.jsp'/>">管理员入口</a>

</body>
</html>
