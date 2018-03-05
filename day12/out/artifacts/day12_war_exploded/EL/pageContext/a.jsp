<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-23
  Time: 下午5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.request.scheme}

<a href="${pageContext.request.contextPath}/EL/a.jsp">点击这里</a>
<form action="${pageCOntext.request.contextPath}/EL/cookie/a.jsp" method="post">
    <input type="submit" value="点击这里"/>
</form>
<hr/>
${pageContext.session.id}
</body>
</html>
