<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-22
  Time: 上午9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn" >点击这里</button>
<h1 id="h1"></h1>

</body>
<script type="text/javascript">
    window.onload = function (ev) { //在文档加载完成后马上执行！
        //得到btn元素
        var btn = document.getElementById("btn");
        //给btn的click事件注册监听器
        btn.onclick = function () {
            //在按钮被点击时执行！
            var h1 = document.getElementById("h1");
            h1.innerHTML = "Hello JS!!!";
        }
    };
</script>
</html>
