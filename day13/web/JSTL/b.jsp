<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-24
  Time: 下午5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Date d = new Date();
    request.setAttribute("date", d);
%>

<fmt:formatDate value="${requestScope.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
<br/>

<%
    request.setAttribute("num1", 3.1);
%>
<fmt:formatNumber value="${requestScope.num1}" pattern="0.00"/>
<br/>
<fmt:formatNumber value="${requestScope.num1}" pattern="#.##"/>
</body>
</html>
