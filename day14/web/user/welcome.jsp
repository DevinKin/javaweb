<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-26
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎登录本系统</h1>
<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">滚!</c:when>
    <c:otherwise>
        ${sessionScope.sessionUser}
    </c:otherwise>
</c:choose>
</body>
</html>
