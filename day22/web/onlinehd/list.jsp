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
    <title>文件列表</title>
</head>
<body>
<table align="center" width="60%" border="1">
    <tr>
        <th>文件名称</th>
        <th>上传时间</th>
        <th>下载次数</th>
        <th>下载链接</th>
    </tr>
    <c:forEach items="${myFilelist}" var="myFile">
    <tr>
        <td>${myFile.framename}</td>
        <td>${myFile.uploadtime}</td>
        <td>${myFile.cnt}</td>
        <td><a href="<c:url value='/MyFileServlet?method=download&fid=${myFile.fid}'/>">下载</a> </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
