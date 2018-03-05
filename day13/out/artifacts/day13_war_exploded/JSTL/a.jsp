<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-24
  Time: 下午3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set var="code" value="<script>alert('Hello');</script>" scope="request"/>
<c:out value="${code}" escapeXml="true"/>
<hr/>

<c:url value="/AServlet"/>
<hr/>
${pageContext.request.contextPath}/AServlet
<br/>
<c:url value="index.jsp">
    <c:param name="name" value="张三"></c:param>
</c:url>
<hr/>

<c:if test="${empty param.name}">
    没有名为name的参数
</c:if>
<hr/>

<c:set var="score" value="${param.score}"/>
<c:choose>
    <c:when test="${score > 100 || score < 0}">错误分数:${score}</c:when>
    <c:when test="${score >= 90}">A级</c:when>
<c:when test="${score >= 80}">B级</c:when>
<c:when test="${score >= 70}">C级</c:when>
<c:when test="${score >= 60}">D级</c:when>
<c:otherwise>E级</c:otherwise>
</c:choose>

<hr/>

<c:forEach var="i" begin="1" end="10" step="2">
    ${i}<br/>
</c:forEach>

<hr/>

<%
    String[] strs = {"one", "two"};
    request.setAttribute("strs", strs);
%>

<c:forEach items="${strs}" var="str">
    ${str}<br/>
</c:forEach>

</body>
</html>
