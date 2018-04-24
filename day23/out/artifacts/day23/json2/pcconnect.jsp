<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-24
  Time: 下午4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>json省市联动</title>
    <script type="text/javascript" src="/js/asynchronous.js"/>
    <script type="text/javascript" src="/ajax-lib/ajaxutils.js"></script>
    <script type="text/javascript">
        window.onload = function (ev) {
            var pdata;
            var pro = document.getElementById("p");
            /**
             * 1. 连接四步
             * 2. 获取所有省对象，并添加到<select id="p">中
             */
            //得到核心对象
            var xmlHttp = createXMLHttpRequest();
            //打开连接
            xmlHttp.open("GET", "<c:url value='/ProServlet'/>", true)
            //发送请求
            xmlHttp.send(null);
            //添加监听
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var text = xmlHttp.responseText;
                    pdata = eval("(" + text + ")");
                    for (var i = 0; i < pdata.length; i++) {
                        //得到province对象
                        var province = pdata[i];
                        var op = document.createElement("option");
                        op.value = province.pid;
                        var textNode = document.createTextNode(province.name);
                        op.appendChild(textNode);
                        pro.appendChild(op);
                    }
                }
            };

            /**
             * 监听select.change事件
             */
            document.getElementById("p").onchange = function () {
                /**
                 * 2. 获取<select id="city">元素
                 */
                var cities = document.getElementById("c");

                /**
                 * 3. 清空<select>元素下的<option>子元素，除了请选择
                 */
                var cityList = cities.getElementsByTagName("option");
                while (cityList.length > 1) {
                    cities.removeChild(cityList[1]);
                }

                /**
                 * 1.连接四步
                 *  1. 获得核心对象
                 *  2. 打开连接
                 *  3. 发送请求("POST")
                 *  4. 监听事件
                 */
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("POST", "<c:url value='/CTServlet'/>", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var req = "pid=" + this.value;
                xmlHttp.send(req);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        var text = xmlHttp.responseText;
                        var responseCities = eval("(" + text + ")");

                        for (var i = 0; i < responseCities.length; i++) {
                            /**
                             * 4. 在<select id="c">中添加<option value="city">子元素
                             */
                            var op = document.createElement("option");
                            op.value = responseCities[i].cid;
                            var textNode = document.createTextNode(responseCities[i].name);
                            op.appendChild(textNode);
                            cities.append(op);
                        }
                    }
                }
            };




        };

    </script>
</head>
<body>
<h1>省市联动</h1>
<select name="province" id="p">
    <option>====请选择====</option>
</select>
&nbsp;&nbsp;
<select name="city" id="c">
    <option>====请选择====</option>
</select>
</body>
</html>
