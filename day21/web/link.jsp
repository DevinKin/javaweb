<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/4/9
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>链接页面</h1>
<a href="<c:url value='/BookServlet?method=findAll'/>">查看所有</a>
<a href="<c:url value='/BookServlet?method=findByCategory&cid=1'/> ">查看SE</a>
<a href="<c:url value='/BookServlet?method=findByCategory&cid=2'/> ">查看EE</a>
<a href="<c:url value='/BookServlet?method=findByCategory&cid=3'/> ">查看FrameWork</a>

</body>
</html>
