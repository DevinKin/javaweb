<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-17
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<!-- 他是jsp指令，也是一种特殊的标签 !-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- JAVA代码的片段 -->
<%
  String path = request.getContextPath(); //获取项目名
  //基础路径,http://localhost:8080/day11
  String basePath = request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
  <head>
    <!-- 向页面输出一个变量 -->
    <base href="<%=basePath%>">
    <title>$Title$</title>
  </head>
  <body>
  This is my JSP page<br/>
  <%
    int a = 10;
  %>
  <%
      out.print(a++);
  %>
  <br/>
  <%= a%>
  <%!
    int a = 100;
    public void fun1() {
        System.out.println(a);
    }
  %>
  <%
    out.print(this.a++);
    fun1();
  %>
  <img src="https://www.baidu.com/img/bd_logo1.png">
  </body>
</html>
