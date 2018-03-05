<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-21
  Time: 下午4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>获取Cookie</h1>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            out.print(c.getName() + "=" + c.getValue() + "<br/>");
        }
    }

    //删除Cookie
    Cookie cookie = new Cookie("aaa","AAA");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
%>
</body>
</html>
