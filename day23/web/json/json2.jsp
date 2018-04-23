<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-23
  Time: 下午7:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value='/js/asynchronous.js'/> "></script>
    <script type="text/javascript">

        window.onload = function (ev) {
            //获取btn元素
            var btn = document.getElementById("btn");
            btn.onclick = function (ev2) {
                //给按钮的点击事件添加监听器
                //使用ajax得到服务器响应，把结果显示到h3中
                /**
                 * 1. 得到XMLHttprequest
                 * 2. 打开连接
                 * 3. 发送请求
                 * 4. 给xmlHttp的状态改变事件添加监听
                 */
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("GET","<c:url value='/DServlet'/> ", true);
                xmlHttp.send(null);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        var text = xmlHttp.responseText;
                        //执行json串
                        var person = eval("(" + text + ")");
                        var s = person.name + ", " + person.age + ", " + person.sex;
                        var h3 = document.getElementById("h3");
                        h3.innerHTML = s;
                    }
                };
            };
        };
    </script>
</head>
<body>
<%--点击按钮后，把服务器响应的数据显示到h3元素中--%>
<button id="btn">点击这里</button>
<h1>JSON之Hello World</h1>
<h3 id="h3"></h3>

</body>
</html>
