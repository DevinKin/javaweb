<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-20
  Time: 下午6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<h1>Devinkin网络硬盘</h1>
<h3>${msg}</h3>
<a href="<c:url value='/onlinehd/upload.jsp'/>">上传页面</a>
<a href="<c:url value='/MyFileServlet?method=findAll'/> ">文件列表</a>
</body>
</html>
