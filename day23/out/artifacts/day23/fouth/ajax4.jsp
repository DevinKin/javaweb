<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-22
  Time: 上午10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        //创建异步对象
        function createXMLHttpRequest() {
            try {
                //支持大多数浏览器
                return new XMLHttpRequest();
            } catch (e) {
                try {
                    //IE6.0
                    return ActiveXObject("Msxm2.XML.HTTP");
                } catch (e) {
                    try {
                        //IE5.5级更早版本的IE
                        return ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        alert("你用的是什么浏览器？")
                        throw e;
                    }
                }
            }
        }

        window.onload = function (ev) {
            //文档加载完毕后执行
            var btn = document.getElementById("btn");
            btn.onclick = function (ev2) {
                //给按钮点击事件注册监听器
                /**
                 * ajax四步操作，得到服务器响应，把响应结果显示到h1元素中
                 * 1. 得到异步对象
                 * 2. 打开与服务器的连接
                 * 3. 发送请求
                 * 4. 给异步对象的onreadystatechange事件注册监听器
                 */
                /**
                 * 1. 得到异步对象
                 */
                var xmlHttp = createXMLHttpRequest();

                /**
                 * 2. 打开与服务器的连接
                 *  1. 指定请求方式
                 *  2. 指定请求的URL
                 *  3. 指定是否为异步请求
                 */
                xmlHttp.open("GET", "<c:url value='/BServlet'/> ", true);

                /**
                 * 3. 发送请求
                 */
                //这次GET请求没有体，但也要给出null，不然Firefox可能会不能发送！
                xmlHttp.send(null);

                /**
                 * 4. 给异步对象的onreadystatechange事件注册监听器
                 */
                //xmlHttp的状态发生变化时执行
                xmlHttp.onreadystatechange = function () {
                    // 双重判断：xmlHttp的状态为4(服务响应结果)，以及服务器响应的状态码为200(响应成功)
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        //获取服务器响应结果
                        var doc = xmlHttp.responseXML;
                        //查找文档下名为student的所有元素，得到数组，再取下标元素0
                        var ele = doc.getElementsByTagName("student")[0];
                        //获取h1元素
                        //获取元素名为number的属性
                        var number = ele.getAttribute("number");
                        var name;
                        var sex;
                        var age;
                        //处理浏览器差异
                        if (window.addEventListener) {
                            //大部分浏览器支持
                            name = ele.getElementsByTagName("name")[0].textContent;
                        } else  {
                            //IE支持
                            name = ele.getElementsByTagName("name")[0].text;
                        }

                        if (window.addEventListener) {
                            //大部分浏览器支持
                            sex = ele.getElementsByTagName("sex")[0].textContent;
                        } else  {
                            //IE支持
                            sex = ele.getElementsByTagName("sex")[0].text;
                        }

                        if (window.addEventListener) {
                            //大部分浏览器支持
                            age = ele.getElementsByTagName("age")[0].textContent;
                        } else  {
                            //IE支持
                            age = ele.getElementsByTagName("age")[0].text;
                        }


                        var text = number + ", " + name + ", " + age + ", "  + sex;
                        document.getElementById("h1").innerHTML = text;

                    }
                }
            }
        };
    </script>
</head>
<body>
    <button id="btn" >点击这里</button>
    <h1 id="h1"></h1>

</body>
</html>
