//创建一个request异步对象
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
 * option对象有如下属性
 * 请求方式：method,
   请求的url: url,
   是否异步: async,
   请求体: params,
   回调方法: callback,
   服务器响应数据转换为什么类型: type
 * @param option
 */
function ajax(option) {
    /**
     * 1. 得到xmlHttp
     */
    var xmlHttp = createXMLHttpRequest();

    /**
     * 2. 打开连接
     */
    if (!option.method)
        option.method = "GET";
    if (option.asyn == undefined) {
        option.asyn = true
    }
    xmlHttp.open(option.method, option.url, option.asyn);
    /**
     * 3. 判断是否为POST
     */
    if ("POST" == option.method) {
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    }
    /**
     * 4. 发送请求
     */
    xmlHttp.send(option.params);

    /**
     * 5. 注册监听
     */
    xmlHttp.onreadystatechange = function () {
        //双重判断
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            //获取服务器的响应数据，进行转换！
            var data;
            if (!option.type) {
                //如果type没有赋值那么，默认为文本
                data = xmlHttp.responseText;

            } else {
                if (option.type == "xml") {
                    data = xmlHttp.responseXML;
                } else if (option.type == "text") {
                    data = xmlHttp.responseText;
                } else if (option.type == "json") {
                    var text = xmlHttp.responseText;
                    data = eval("(" + text + ")");
                }
            }
        }
        //调用回调方法
        option.callback(data);
    }
}
