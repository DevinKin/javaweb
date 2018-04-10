<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/1
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>网页统计IP访问数量</title>
</head>
<body>
<h1 align="center">网页统计IP访问数量</h1>
<table align="center" width="60%" border="1">
    <tr>
        <td>IP地址</td>
        <td>访问次数</td>
    </tr>
    <c:forEach items="${applicationScope.ipMap }" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
