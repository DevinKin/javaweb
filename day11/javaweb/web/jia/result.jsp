<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-18
  Time: 下午8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Integer result = (Integer)request.getAttribute("result");
%>
<%=result %>
</body>
</html>
