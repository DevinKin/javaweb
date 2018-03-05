<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-26
  Time: 下午4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注册</h1>
<p style="color: red; font-weight: 900;">${msg}</p>
<form action="<c:url value="/RegistServlet"/>" method="post">
    用户名：<input type="text" name="username" value="${user.username}"/>&nbsp;&nbsp;
    <b style="color: red; font-weight: 500;" >${errors.username}</b><br/>
    密 码：<input type="password" name="password" value="${user.password}"/>&nbsp;&nbsp;
    <b style="color: red; font-weight: 500;" >${errors.password}</b><br/>
    验证码: <input type="text" name="verifyCode" size="3" value="${user.verifyCode}"/>&nbsp;&nbsp;
    <img id="verifyCode" src="<c:url value='/VerifyCodeServlet'/>" />
    <a href="javascript:_change()">看不清,换一张 &nbsp;&nbsp;</a>
    <b style="color: red; font-weight: 500;" >${errors.verifyCode}</b>
    <br/>
    <input type="submit" value="注册"/>
</form>

</body>
<script type="text/javascript">
    function _change() {
        /**
         * 1.获取img元素
         */
        var ele = document.getElementById("verifyCode");
        ele.src="<c:url value='/VerifyCodeServlet' />?xxx=" + new Date().getTime();
    }

</script>
</html>
