<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 18-4-22
  Time: 下午10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        //创建一个异步对象
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

        /**
         * 1.在文档加载完毕时发出请求，得到所有省份名称，显示在<select name="province">中
         * 2.在选择了新的省份时，发送请求(参数为省名称），得到xml文档，即<province>元素
         * 3.解析xml文档，得到其中所有的<city>，再得到每个<city>元素的内容，即市名，使用市名生成<option>，添加到<select name="city">中
         */
        window.onload = function (ev) {
            /**
             * 1. ajax四步，请求ProvinceServlet，得到所有的省份名称
             * 2. 使用每个省份名称创建一个<option>元素，添加到<select name="province">
             */
            var xmlHttp = createXMLHttpRequest();
            xmlHttp.open("GET", "<c:url value='/ProvinceServlet'/>", true);
            xmlHttp.send(null);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    //获取服务器的响应
                    var text = xmlHttp.responseText;
                    //使用逗号分隔它，得到数组
                    var arr = text.split(",");
                    //循环遍历数组，得到每一个省份的名称，用每一个省份的名称创建一个<option>元素，添加到<select name="province">元素中
                    for (var i = 0; i < arr.length; i++) {
                        //创建一个指定名称的元素
                        var op = document.createElement("option");
                        op.value = arr[i];
                        //创建文本节点
                        var textNode = document.createTextNode(arr[i]);
                        //把文本子节点添加到op元素中，指定其显示值
                        op.appendChild(textNode);

                        //把option添加到select中
                        document.getElementById("p").appendChild(op);
                    }
                }
            };

            /**
             * 2. 第二个事情：给<select name="province">添加改变监听器
             * 使用省份名称请求CityServlet，得到<province>元素(xml元素)!!!
             * 获取<province>元素中的所有的<city>元素，遍历之，获取每个<city>的文本内容，即市名
             * 使用每个市名称创建<option>元素添加到<select name="city">
             */
            var proSelect = document.getElementById("p");
            proSelect.onchange = function (ev) {
                /**
                 * 1. ajax四步，请求ProvinceServlet，得到所有的省份名称
                 * 2. 使用每个市名名称创建一个<option>元素，添加到<select name="city">
                 */
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("POST", "<c:url value='/CityServlet'/>", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlHttp.send("pname=" + proSelect.value);
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        /**
                         * 把select中的所有option移除，除了请选择
                         */
                        var citySelect = document.getElementById("c");
                        var optionList = citySelect.getElementsByTagName("option");
                        //循环遍历每个option元素，然后在citySelect中移除
                        while (optionList.length > 1)
                            citySelect.removeChild(optionList[1]);


                        var doc = xmlHttp.responseXML;
                        //得到所有名为city的元素
                        var cityEleList = doc.getElementsByTagName("city");
                        //循环遍历每个city元素
                        for (var i = 0; i < cityEleList.length; i++) {
                            //创建一个指定名称的元素
                            var cityEle = cityEleList[i];
                            var cityName;
                            //处理浏览器的差异
                            if (window.addEventListener) {
                                cityName = cityEle.textContent;
                            } else {
                                cityName = cityName.text;
                            }
                            //使用市名创建option元素，添加到<select name="city">中
                            var op  = document.createElement("option");
                            op.value = cityName;

                            //创建文本节点
                            var textNode = document.createTextNode(cityName);
                            //把文本节点追加到op元素中
                            op.appendChild(textNode);

                            //把op元素添加到<select元素中
                            var selectEle = document.getElementById("c");
                            selectEle.appendChild(op);
                        }
                    }
                };
            };
        };


    </script>
</head>
<body>
<h1>省市联动</h1>
<select name="province" id="p">
    <option>====请选择省份====</option>
</select>
&nbsp;&nbsp;
<select name="city" id="c">
    <option>====请选择城市====</option>
</select>

</body>
</html>
