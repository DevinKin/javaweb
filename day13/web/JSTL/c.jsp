<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-24
  Time: 下午6:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="it" uri="/WEB-INF/tlds/itcast-tag.tld"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<it:myTag5 test="${empty param.xxx}">
    <h1><it:myTag4/></h1>
</it:myTag5>
<h1><it:myTag1/></h1>
<h2><it:myTag2/></h2>
<hr/>

<%
    request.setAttribute("xxx","zhangsan");
%>
<h3>
    <it:myTag3>${xxx}</it:myTag3>
</h3>
<h3>
    <it:myTag3>我是所在们的老爸</it:myTag3>
</h3>


</body>
</body>
</html>
