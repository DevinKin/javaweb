<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-23
  Time: 下午9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value='/ajax-lib/ajaxutils.js'/>"></script>
    <script type="text/javascript">
        window.onload = function (ev) {
            var btn = document.getElementById("btn");
            btn.onclick = function (ev2) {
                /**
                 * 1. 发送ajax请求
                 */
                ajax(
                    {
                        "url":"<c:url value='/DServlet'/>",
                        type:"json",
                        callback:function (data) {
                            var h3 = document.getElementById("h3");
                            h3.innerHTML = data.name + ", " + data.age + ", " + data.sex;
                        }
                    }
                );
            };
        };
    </script>
</head>
<body>
<h1>演示自己封装的小工具</h1>
<%--点击按钮后，把服务器响应的数据显示到h3元素中--%>
<button id="btn">点击这里</button>
<h1>JSON之Hello World</h1>
<h3 id="h3"></h3>

</body>
</html>
