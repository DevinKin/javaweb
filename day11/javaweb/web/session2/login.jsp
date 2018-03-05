<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-21
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎登录</h1>
<%
    String initName = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("user".equals(c.getName()))
                initName = c.getValue();
        }
    }
%>
<form action="/day11/LoginServlet" method="post">
    用户名: <input type="text" name="username" value="<%=initName%>"/><br/>
    密  码: <input type="password" name="password"/><br/>
    验证码: <input type="text" name="verifyCode" size="3"/>
    <img id="img" src="/day11/VerifyCodeServlet"/>
    <a href="javascript:_change()">换一张</a>
    <br/>
    <input type="submit" value="登录"/><br/>
</form>
<%
    String msg = "";
    String errorInfo = (String) request.getAttribute("userError");
    if (errorInfo != null)
        msg = errorInfo;

    errorInfo = (String) request.getAttribute("noLogin");
    if (errorInfo != null)
        msg = errorInfo;

    errorInfo = (String) request.getAttribute("VerifyCodeError");
    if (errorInfo != null)
        msg = errorInfo;
%>
<font color="red"><b><%=msg%></b></font>
</body>
<script>
    function _change() {
        /**
         * 1.得到img元素
         * 2.修改其src为/day11_3/VerifyCodeServlet
         */
        var imgEle = document.getElementById("img");
        imgEle.src = "/day11/VerifyCodeServlet?a=" + new Date().getTime();
    }
</script>
</html>
