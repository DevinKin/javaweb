<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-18
  Time: 下午8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" align="center" width="60">
    <tr>
        <td>姓名</td>
        <td>年龄</td>
    </tr>

<%
    for(int i = 0; i < 10; i++) {
%>
<tr>
    <td>张三</td>
    <td>28</td>
</tr>

<%
    }
%>

</table>
</body>
</html>
