<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-23
  Time: 下午7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        window.onload = function (ev) {
            var str = "{\"name\" : \"zhangSan\", \"age\":18, \"sex\": \"male\"}";
            var person = eval("(" + str + ")");
            alert(person.name + ", " + person.age + ", " + person.sex);
        }
    </script>
</head>
<body>

</body>
</html>
