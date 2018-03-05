<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-22
  Time: 上午1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/AServlet";JSESSIONID="<%=session.getId()%>">点击这里</a>
<%
    //它会查看cookie是否存在,如果不存在,在制定的url后添加JSSESIONID参数
    //如果cookie存在,它就不会在url后面添加任何东西
    out.println(response.encodeURL("/JServlet"));
%>
</body>
</html>
