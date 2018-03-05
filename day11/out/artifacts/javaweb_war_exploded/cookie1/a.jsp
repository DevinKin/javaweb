<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-21
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>保存Cookie</h1>
<%-- request,response,session,application,pageContext,config,out,page,exception --%>
<%
    Cookie cookie1 = new Cookie("aaa","AAA");
    //演示Cookie的MaxAge
    cookie1.setMaxAge(60*60);
    response.addCookie(cookie1);

    Cookie cookie2 = new Cookie("bbb","BBB");
    response.addCookie(cookie2);
%>
</body>
</html>
