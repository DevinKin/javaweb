<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-21
  Time: 下午7:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    String eInfo = null;
    //如果用户名不存在
    if (username == null) {
        eInfo = "你还没有登录,请登录后进入该页面";
        //在request域中保存错误信息
        request.setAttribute("noLogin", eInfo);
        //转发到LoginServlet中
        request.getRequestDispatcher("/session2/login.jsp").forward(request, response);
    }
%>
<h1>登录成功</h1>
<%=username%>

</body>
</html>
