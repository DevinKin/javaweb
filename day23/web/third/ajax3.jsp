<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-22
  Time: 下午5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>演示用户名是否被注册</title>
    <style>
        #errorSpan {
            color:red;
        }
    </style>
    <script type="text/javascript">
        function createXMLHttpRequest() {
            try {
                return new XMLHttpRequest();
            } catch (e) {
                try{
                    //IE6.0
                    return ActiveXObject("Mxxm2.XML.HTTP");
                } catch (e) {
                    try {
                        //IE5.0或以前的浏览器
                        return ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        alert("你用的是什么浏览器？");
                        throw e;
                    }
                }
            }

        }

        window.onload = function (ev) {
            // 获取文本框，给它失去焦点事件注册监听器
            var userEle = document.getElementById("usernameEle");
            userEle.onblur = function (ev2) {
                /**
                 * 1.得到异步对象
                 */
                var xmlHttp = createXMLHttpRequest();

                /**
                 * 2. 打开连接
                 */
                xmlHttp.open("POST", "<c:url value='/ValidateUsernameServlet'/>", true);

                /**
                 * 3. 设置请求头
                 */
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                /**
                 * 4. 发送请求，给出请求体
                 */
                xmlHttp.send("username=" + userEle.value);

                /**
                 * 5. 给xmlHttp的onreadystatechange事件注册监听器
                 */
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        /**
                         * 1. 获取服务器的响应，判断是否为1
                         * 2. 是：获取span，添加内容：”用户名已经被注册“
                         */
                        var text = xmlHttp.responseText;
                        var span = document.getElementById("errorSpan");
                        if (text == "1") {
                            //得到span元素
                            span.innerHTML = "用户名已经被注册了";
                        } else {
                            span.innerHTML = "";
                        }
                    }
                }
            };
        };
    </script>
</head>
<body>
<h1>演示用户名是否被注册</h1>
<form action="" method="post">
    用户名：<input type="text" name="username" id="usernameEle"/>&nbsp;&nbsp;<span id="errorSpan"></span><br/>
    密码：<input type="password" name="password" /><br/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
