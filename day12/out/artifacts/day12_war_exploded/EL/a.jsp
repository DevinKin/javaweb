<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-23
  Time: 上午11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.domain.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Address address = new Address();
    address.setCity("北京");
    address.setStreet("西三旗");

    Employee emp = new Employee();
    emp.setName("张三");
    emp.setSalary(123456);
    emp.setAddr(address);

    request.setAttribute("emp", emp);
%>

<h3>使用el获取request域的emp</h3>
${requestScope.emp.addr.street}
${emp.hehe}

</body>
</html>
