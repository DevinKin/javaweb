<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function _go() {
			var pc = $("option[selected=true]").val();
			location="${pb.url}&pc=" + pc;
		}
	</script>
  </head>
  
  <body>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
<c:forEach items="${pb.beanList }" var="customer">
	<tr>
		<td>${customer.cname }</td>
		<td>${customer.gender }</td>
		<td>${customer.birthday }</td>
		<td>${customer.cellphone }</td>
		<td>${customer.email }</td>
		<td>${customer.description }</td>
		<td>
			<a href="<c:url value='/CustomerServlet?method=loadForEdit&cid=${customer.cid }'/>">编辑</a>
			<a href="<c:url value='/CustomerServlet?method=delete&cid=${customer.cid }'/>">删除</a>
		</td>
	</tr>
</c:forEach>
</table>
<br/>
<center>
	第${pb.pc }页/共${pb.tp }页
	
	<a href="${pb.url }&pc=1">首页</a>
	<c:choose>
		<c:when test="${pb.pc eq 1 }">上一页</c:when>
		<c:otherwise>
			<a href="${pb.url }&pc=${pb.pc-1 }">上一页</a>
		</c:otherwise>
	</c:choose>

<%--
页码列表，最多显示10个页码！当前页在5位置上，例如：1 2 3 4 (5) 6 7 8 9 10
 --%>
<c:choose>
	<c:when test="${pb.tp <= 10 }">
		<c:set var="begin" value="1"/>
		<c:set var="end" value="${pb.tp }"/>
	</c:when>
	<c:otherwise>
		<c:set var="begin" value="${pb.pc-4 }"/>
		<c:set var="end" value="${pb.pc+5 }"/>
		<c:choose>
			<c:when test="${begin < 1 }">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="10"/>				
			</c:when>
			<c:when test="${end > pb.tp }">
				<c:set var="begin" value="${pb.tp-9 }"/>
				<c:set var="end" value="${pb.tp }"/>			
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
<c:forEach begin="${begin }" end="${end }" var="i">
	<c:choose>
		<c:when test="${i == pb.pc }">${i }</c:when>
		<c:otherwise><a href="${pb.url }&pc=${i}">[${i }]</a></c:otherwise>
	</c:choose>
</c:forEach>


	<c:choose>
		<c:when test="${pb.pc eq pb.tp }">下一页</c:when>
		<c:otherwise>
			<a href="${pb.url }&pc=${pb.pc+1 }">下一页</a>
		</c:otherwise>
	</c:choose>
	<a href="${pb.url }&pc=${pb.tp }">尾页</a>
	
	<select name="pc" onchange="_go()">
<c:forEach begin="1" end="${pb.tp }" var="i">
		<option value="${i }" <c:if test="${i == pb.pc }">selected=='selected'</c:if>>${i }</option>
</c:forEach>
	</select>
</center>
  </body>
</html>
