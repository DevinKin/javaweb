# ajax 简介
1. ajax是什么
    1. asynchronous javascript and xml：异步交互的js和xml
    2. 它能使用js访问服务器，而且是异步访问！
    3. 服务器给客户端响应一般是整个页面，一个html完整页面！但在ajax中因为是局部刷新，那么服务器就不用再响应整个页面了！而只是数据
        1. text：纯文本
        2. xml: xml文本
        3. json：它是js提供的数据交互格式，它在ajax中最受欢迎！ 
        
2. 异步交互和同步交互
    1. 同步：
        1. 发一个请求，就要等待服务器的响应结束，然后才能发送第二个请求。中间一段时间都在等待
        2. 刷新的是整个页面
      
    2. 异步：
        1. 发一个请求，无需等待服务器的响应，然后就可以发第二个请求！
        2. 可以使用js来接收服务器的响应，然后使用js来局部刷新
              
3. ajax应用场景
    1. 百度的搜索框
    2. 用户注册时(校验用户是否被注册过)

# ajax的优缺点
1. 优点：
    1. 异步交互：增强了用户体验
    2. 性能：因为服务器无需再响应整个页面，只需要响应部分内容，所以服务器的压力减轻了
   
2. 缺点：
    1. ajax不能应用在所有场景
    2. ajax无端的增多了对服务器的访问次数，给服务器带来了压力！
   

# ajax发送异步请求(四步操作)
1. 第一步(得到XMLHttpRequest)：
    1. ajax其实只需要学习一个对象：XMLHttpRequest，如果掌握了它，就掌握了ajax。
    2. 得到XMLHttpRequest
        1. 大多数浏览器都支持 `var xmlHttp = new XMLHttpRequest();`
        2. IE6.0：`var xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");`
        3. IE5.0：以更早版本的IE： `var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");`
    3. 编写创建XMLHttpRequest对象的函数
        ```javascript
           function createXMLHttpRequest() {
               try {
                   return new XMLHttpRequest();    
               } catch (e) {
                   try {
                       return new ActiveXObject("Msxml2.XMLHTTP");
                   } catch (e) {
                       try {
                           return new ActiveXObject("Microsoft.XMLHTTP");
                       } catch (e) {
                           alert("你用的是什么浏览器？");
                           throw e;
                       }
                   }
               }
           }
        ```
        
2. 第二步(打开与服务器的连接)        
    1. `xmlHttp.open()`：用来打开与服务器的连接，它需要三个参数
        1. 请求方式：可以是GET或POST
        2. 请求的URL：指定服务器端资源，例如：/day23/AServlet
        3. 请求是否为异步，如果为true表示发送异步请求，否则同步请求！
    2. `xmlHttp.open("GET", "/day23/AServlet", true)`
    
3. 第三步(发送请求)    
    1. `xmlHttp.send(null)`：如果不给可能会造成部分浏览器无法发送
        1. 参数：就是请求体内容！如果是GET请求，必须给出null。
    2. `xmlHttp.send("username=zhangsan&password=123")`
        1. POST请求给出请求体
     
4. 第四步(设置xmlHttp对象的事件监听器)     
    1. 在xmlHttp对象的一个事件上注册监听器！`onreadystatechange`事件
    2. xmlHttp对象一共有5个状态
        1. 0：初始化未完成状态，只是创建了XMLHttpRequest对象，还没调用open()方法
        2. 1：请求已开始，open()方法已经调用，但还没有调用send()方法
        3. 2：请求发送完成状态，send()方法已调用
        4. 3：开始读取服务器响应，不表示服务器响应结束
        5. 4：读取服务器响应结束
    3. 得到xmlHttp对象的状态：
        1. `var state = xmlHttp.readyState; //可能是0，1,2,3,4,5` 
    4. 得到服务器响应的状态码
        1. `var status = xmlHttp.status // 例如200,404,500`
    5. 得到服务器响应的内容
        1. 得到服务器响应的文本格式的内容：<br>
        `var content = xmlHttp.responseText;`
        2. 得到服务器响应的xml响应的内容，它是document对象<br/>
        `var content = xmlHttp.responseXML;`

5. 例：
```javascript
xmlHttp.onreadystatechange = function() {
    //xmlHttp的5种状态都会调用本方法
    //双重判断，判断是否为4状态，判断是否为200
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var text = xmlHttp.responseText;
    }
}
```       


# ajax第一例
```jsp
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
                xmlHttp.open("GET", "<c:url value='/AServlet'/> ", true);

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
                        var text = xmlHttp.responseText;
                        //获取h1元素
                        var h1 = document.getElementById("h1");
                        h1.innerText = text;
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
```
```java
package cn.devinkin.first.web.servlet;

import java.io.IOException;

public class AServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Hello AJAX!");
        response.getWriter().print("Hello AJAX!!!");
    }
}
```

# ajax第二例：发送POST请求(如果发送请求时需要带有参数，一般都用POST)
1. open： xmlHttp.open("POST", ...);

2. 添加一步：设置Content-Type请求头<br/>
    `xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");`
3. send: 发送请求时候指定请求体
    `xmlHttp.send("username=zhangsan&password=123")`

4. 例：
```jsp
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
                /*************************修改open方法，指定请求方式为POST*************************/
                xmlHttp.open("POST", "<c:url value='/AServlet'/> ", true);
                /*************************设置请求头：Content-Type*************************/
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")

                /**
                 * 3. 发送请求
                 */
                /*************************发送时指定请求体*************************/
                xmlHttp.send("username=张三&password=123");

                /**
                 * 4. 给异步对象的onreadystatechange事件注册监听器
                 */
                //xmlHttp的状态发生变化时执行
                xmlHttp.onreadystatechange = function () {
                    // 双重判断：xmlHttp的状态为4(服务响应结果)，以及服务器响应的状态码为200(响应成功)
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        //获取服务器响应结果
                        var text = xmlHttp.responseText;
                        //获取h1元素
                        var h1 = document.getElementById("h1");
                        h1.innerText = text;
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
```

```java
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        System.out.println("(POST:) Hello AJAX!" + username);
        resp.getWriter().print("Hello AJAX!!!" + username);
    }
```


# ajax第三例：注册表单之校验用户是否注册！
1. 编写页面：
    1. ajax3.jsp
        1. 给出出册表单页面
        2. 给用户名文本框添加onblur事件(文本框失去焦点时的事件)的监听
        3. 获取文本框的内容，通过ajax 4步发送给服务器，得到响应结果
            1. 如果为1，在文本框后显示用户名“用户名已被注册”
            2. 如果为0，什么都不做
    
2. 编写Servlet
    1. ValidateUsernameServlet
        1. 获取客户端传递的用户名参数 
        2. 判断是否为itcast
            1. 是：返回1
            2. 否：返回0
        
        
# 第四例：响应内容为xml数据
1. 服务器端：
    1. 设置响应头：ContentType，其值为：`text/xml;charset=utf-8`

2. 客户端：
    1. 得到Document对象：`var doc = xmlHttp.responseXML;`
   
3. 例
```java
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
```


# 第五例：省市联动
1. 页面
    ```html
       <select name = "province">
           <option>===请选择省份===</option>
       </select>
       <select name="city">
           <optino>===请选择城市===</option>
       </select>
    ```

2. ProvinceServlet 
    1. ProvinceServlet：当页面加载完毕后就马上请求这个Servlet！
        1. 他需要加载china.xml文件，把所有的省的名称使用字符串发送给客户端！
        
    2. 页面的工作
        1. 获取这个字符串，使用逗号分隔，得到数组
        2. 循环遍历每个字符串(省份的名称)，使用每个字符串创建一个`<option>`元素添加到`<select name="province">`这个元素中
        
3. CityServlet
    1. CityServlet：当页面选择某个省时，发送请求！
    2. 得到省份的名称，加载china.xml文件，查询出省份对应的元素对象！把这个元素转换成xml元素，发送给客户端
    
5. 页面的工作
    1. 把`<select name="city">`中的所有子元素删除，除了`<optino>===请选择城市===</option>`
    1. 得到服务器的响应结果：doc
    2. 获取所有的`<city>`子元素，循环遍历得到`<city>`的内容
    3. 使用每个`<city>`的内容创建一个`<option>`元素，添加到`<select name="city">`
    
    
    
# XStream
1. 什么作用
    1. 可以把JavaBean转换为(序列化为)xml
    
2. XStream的jar包
    1. 核心JAR包：xstream-1.4.7.jar
    2. 必须依赖包：xpp3_min-1.1.4c（XML Pull Parser，一款速度很快的XML解析器）
    
3. 使用步骤
    1. `XStream xstream = new XStream()`
    2. `String xmlStr = xstream.toXML(javabean)`
        
4. 使用细节
    1. 别名：把类型对应的元素名修改了
        ```java
           //让List类型生成的元素名为china
           xStream.alias("china", List.class);
           //让Province类型生成的元素名为province
           xStream.alias("province",Province.class);
        ```
    2. 使用为属性：默认类的成员，生成的是元素的子元素！我们希望让类的成员生成元素的属性
        ```java
           //把Province类的名为name的成员，生成<province>元素的name属性
           xStream.useAttributeFor(Province.class, "name");
        ```
    3. 去除Collection类型的成员：我们只需要向Collection的内容，而不希望Collection本身也生成一个元素
        ```java
           //让Province类的名为cities成员不生成元素(它是List类型，它的内容还会生成元素）
           xStream.addImplicitCollection(Province.class, "cities");
        ```
    4. 去除类的指定成员，让其不生成xml元素
        ```java
           //在生成的xml中不会出现City类的，名为description的对应元素！
           xStream.omitField(City.class, "description");
        ```
        
# JSON
1. json是什么？
    1. 它是js提供的一种数据交换格式！
   
2. json的语法
    1. {}：是对象！
        1. 属性名必须用双引号括起来！单引号不行！！！！
     
    2. 属性值：
        1. null
        2. 数值
        3. 字符串
        4. 数组：使用[]括起来
        5. boolean值：true和false
        6. 对象：用{}括起来
      
3. 应用json
    1. `var person = {"name":"zhangsan", "age":18, "sex":"male"}`
    
4. json与xml比较
    1. XML可读性比较好
    2. 解析难度：JSON本身就是JS对象，所以简单很多
    3. 流行度：XML已经流行好多年，但在AJAX领域，JSON更受欢迎
    
    
# json-lib
1. json-lib是什么
    1. 它可以把javabean转换为字符串
        
2. json-lib核心jar包
    1. json的核心jar包有：
        1. json-lib.jar
    2. json-lib的依赖jar包有：
        1. commons-lang.jar
        2. commons-beanutils.jar
        3. commons-logging.jar
        4. commons-collections.jar
        5. ezmorph.jar

3. 核心类
    1. JSONObject --> Map
        1. toString()
        2. `JSONObject map = JSONObject.fromObject(person)`把对象转换成JSONObject对象
    2. JSONArray --> List
        1. toString()
        2. `JSONArray jsonArray = JSONObject.fromObject(list)`把对象转换成JSONArray对象
        
        
# json省市联动
1. dao：提供两个方法
    1. 一个是查询所有省
    2. 通过省名查询指定的市
    
2. servlet：两个方法
    1. 一个把省转换成json，发送给客户端
    2. 通过获取省份名称这个参数，然后查询该省下的所有市，转换成json，发送给客户端
    
3. ajax1.jsp
    1. 页面加载完成后：访问servlet，得到所有省，然后显示在`<select id="province">`
    2. 页面加载完成后：给`<select id="province>`添加onchange事件监听，获取选择的省名称，访问servlet，得到所有市，显示在`<select id="city">`中
    
    
4. 一行记录对应多个对象时，需要使用Map来生成多个对象
    1. 