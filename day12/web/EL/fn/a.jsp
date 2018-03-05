<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-23
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
    <h1>JSTL提供的EL函数库</h1>
<body>
<%
    String[] strs = {"a", "b", "c"};
    List<String> list = new ArrayList<String>();
    list.add("a");
    pageContext.setAttribute("arr", strs);
    pageContext.setAttribute("list", list);
%>

${fn:length(arr)} <br/>     <!--3-->
${fn:length(list)}<br/>     <!--1-->
${fn:toLowerCase("Hello")}<br/>   <!--hello-->
${fn:toUpperCase("Hello")}<br/>   <!--HELLO-->
${fn:contains("abc","a" )}<br/>   <!--true-->
${fn:containsIgnoreCase("abc","Ab" )}<br/>  <!--true-->
${fn:endsWith("Hello.java",".java" )}<br/>  <!--true-->
${fn:startsWith("Hello.java", "Hell" )}<br/>  <!--true-->
${fn:indexOf("Hello-World","-" )}<br/>      <!--5-->
${fn:join(arr,";")}<br/>    <!--a;b;c-->
${fn:replace("Hello-world","-" ,"+" )}<br/><!--Hello+World-->
${fn:join(fn:split("a;b;c;", ";"), "-")}<br/><!-- a-b-c -->
${fn:substring("0123456789",6 ,9 )}<br/><!--678-->
${fn:substring("0123456789",5 ,-1 )}<br/><!--56789-->
${fn:substringAfter("Hello-World","-" )}<br/><!--World-->
${fn:substringBefore("Hello-World","-" )}<br/><!--Hello-->
${fn:trim("   a b c    ")}<br/><!--a b c -->
${fn:escapeXml("<html></html>")} <!--<html></html>-->

</body>
</html>
