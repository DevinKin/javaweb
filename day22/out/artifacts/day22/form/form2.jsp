<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-14
  Time: 下午11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>上传2</title>
</head>
<body>
<h1>上传2</h1>
<form action="<c:url value='/UploadServlet2'/> " method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/><br/>
    照片：<input type="file" name="zhaoPian"/><br/>
    <input type="submit" value="上传">
</form>

</body>
</html>
