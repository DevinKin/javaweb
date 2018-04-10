<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/9
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
<h1 align="center">图书列表</h1>
<table align="center" border="1">
    <tr>
        <th>书名</th>
        <th>单价</th>
        <th>分类</th>
    </tr>
    <c:forEach items="${bookList}" var="book">
        <tr>
            <td>${book.bname}</td>
            <td>${book.price}</td>
            <td>${book.category}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
