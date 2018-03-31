<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/3/30
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>获取session中的数据</h1>

<%
    out.print(session.getAttribute("xxx"));
    out.print(session.getAttribute("user2"));
%>

</body>
</html>
