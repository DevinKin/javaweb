<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-22
  Time: 下午8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String name = "zhangsan";
    String pagePath = "lo.jsp";
%>
<%@include file=<%=pagePath%>"%>

</body>
</html>
