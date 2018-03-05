<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-2-23
  Time: 下午2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%---map.key这是el的语法！requestScope.xxx
    map['key']也可以操作map
    --%>

${param.username}<br/>
${paramValues.hobby[0]}
${paramValues.hobby[1]}

</body>
</html>
