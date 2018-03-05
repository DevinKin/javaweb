<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-23
  Time: 上午12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //pageContext.setAttribute("xxx", "pageContext_XXX");
    //request.setAttribute("xxx", "request_XXX");
    //session.setAttribute("xxx", "session_XXX");
    application.setAttribute("xxx", "application_XXX");
%>

${xxx}<br/>
${pageScope.xxx}<br/>
${requestScope.xxx}<br/>
${sessionScope.xxx}<br/>
${applicationScope.xxx}<br/>


</body>
</html>
