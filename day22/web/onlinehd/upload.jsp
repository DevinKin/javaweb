<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-20
  Time: 下午6:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>
<form action="<c:url value='/MyFileUploadServlet'/>" method="post" enctype="multipart/form-data">
    <input type="file" name="myfile"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
