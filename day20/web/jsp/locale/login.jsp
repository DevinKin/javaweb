<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2018/3/31
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
把与语言相关的所有字符串都写成变量！！！
--%>
<%
    /**
     * 1. 获取Locale，这是由客户端的浏览器提供的Locale
     * 2. 创建ResourceBundle
     * 3. 把所有的语言信息使用rb.getString("xxx")来替换！
     */
    Locale locale = request.getLocale();
    ResourceBundle rb = ResourceBundle.getBundle("res", locale);
%>
<h1><%=rb.getString("login")%></h1>
<form action="" method="post">
    <%=rb.getString("username")%>：<input type="text" name="username"/><br/>
    <%=rb.getString("password")%>：<input type="password" name="password"/><br/>
    <input type="submit" value="<%=rb.getString("login")%>"/>
</form>

</body>
</html>
