# 文件上传(不能使用BaseServlet)
## 一、文件上传对页面的要求
1. 必须使用表单，而不能是超链接
### 1、上传对表单的限制
1. 表单的method必须是POST，而不是GET
2. 表单的enctype必须是 multipart/form-data,enctpye="multipart/form-data"
3. 表单中需要添加文件表单项：`<input type="file" name="xxx"/>`

```html
<form action="xxx" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/><br/>
    照片：<input type="file" name="zhaoPian"/><br/>
    <input type="submit" value="上传">
</form>
```

### 2、上传对Servlet的限制
1. request.getParameter("xxx")；这个方法在表单为enctype="multipart/form-data"时，它作废了，它永远返回null
2. ServletInputStream request.getInputStream()；包含整个请求的体！

### 3、多部件表单的体
1. 每隔出多个部件，即一个表单项一个部件。
2. 一个部件自己包含请求头和空行，以及请求体
3. 普通表单项：<br/>
    (1)一个头：Content-Disposition：包含name="xxxx"，即表单项名称。<br/>
    (2)体就是表单项的值 
4. 文件表单项<br/>
    (1)2个头：
        Content-Disposition：包含name="xxx"，即表单项名称：还有一个filename="xxx"，表示上传文件的名称<br/>
        Content-type：它是上传文件的MIME类型，例如：image/jpeg，表示上传的是图片，图上中jpg扩展名的图片<br/>
    
    (2)体就是长传文件的内容
    

### commons-fileupload
依赖的jar包
1. commons-fileupload.jar
2. commons-io.jar

这个小组件，它会帮我们解析request中的上传数据。解析后的结果是一个表单项数据封装到一个FileItem对象中，我们只需调用FileItem的方法即可！


## 二、上传的三步工作
### 1、 相关类：
1. 工厂：DiskFileItemFactory
2. 解析器：ServletFileUpload
3. 表单项：FileItem

### 2、步骤
1. 创建工厂：DiskFIleItemFactory factory = new DiskFileItemFactory();
2. 创建解析器：ServletFileUpload sfu = new ServletFileUpload(factory);
3. 使用解析器来解析request，得到FileItem集合：List<FileItem>  fileItemList = sfu.parseRequest(request);

### 3、FileItem的API
1. boolean isFormField()：是否为普通表单项 
2. String getFieldName()：返回当前表单项名称
3. String getString(String charset)：获取返回表单项的值；
4. String getName()：返回上传的文件名称
5. long getSize()：返回上传文件的字节数
6. InputStream getInputStream()：返回上传文件对应的输入流 
7. void write(FIle destFile)：把上传的文件内容保存到指定的文件中。
8. String getContentType(); 获取MIME类型


## 三、上传的细节
### 文件的保存路径为WEB-INF目录下
1. 目的是不让浏览器直接访问到
2. 把文件保存到WEB-INF目录下！

### 文件名称相关问题
1. 有的浏览器上传的文件名是绝对路径，这需要切割！该路径都是为客户端文件的路径
```java
String filename = fi2.getName();
int index = filename.lastIndexOf("/");
if (index != -1) {
    filename = filename.substring(index+1);
}
```
2. 文件名乱码或普通表单项乱码：request.setCharacterEncoding("UTF-8");因为fileupload内部会调用request.getCharacterEncoding();
```java
//优先级低
request.setCharacterEncoding("UTF-8");
//这种优先级高于上一种
ServletFileUpload.setEncoding(String);     
```
3. 上传文件同名问题：我们需要为每个文件添加名称前缀，这个前缀要保证不能重复。uuid
```java
filename = CommonUtils.uuid() + "_" + filename;
```

### 目录打散
1. 不能在一个目录下存放过多文件。

2. 首字符打散，使用文件的首字母作为目录名称，例如：abc.txt，那么我们把文件保存到a目录下。如果a目录这时不存在，那么创建之

3. 时间打散，使用当前日期作为目录。

4. 哈希打散：
    1. 通过文件名称得到一个int值，即调用`hashCOde()`方法
    2. 把int值转换成十六进制0-9、A-F
    3. 获取十六进制的前两位用来生成目录，目录为两层！例如：1B2C3D4E5F，/1/B/保存文件

### 上传的文件的大小限制
1. 单个文件大小限制
```java
sfu.setFileSizeMax(100 * 1024);  //限制单个文件为100K
```
该方法调用，必须在解析开始之前调用！
```java
sfu.parseRequest(request);
```
如果上传的文件超出限制，在parseRequest方法会抛出异常

2. 整个请求所有数据大小限制
    1. `sfu.setSizeMax(1024*1024);  //限制本个表单大小为1M`
    2. 这个方法也是必须在parseRequest()方法之前调用
    3. 如果上传的文件超出限制，在parseRequest()方法执行时，会抛出异常，FileUploadBase.SizeLimitExceededException

### 缓存大小与临时目录 
1. 缓存大小超出多大，才向硬盘保存！默认为10KB。
2. 临时目录：向硬盘的什么目录保存
    1. 设置缓存大小与临时目录：
        ```java
        DiskFileItemFactory factory = new DiskFileItemFactory(int sizeThreshold, File repository);
        ```


# 下载
1. 下载就是向客户端响应字节数据！
    1. 原来我们响应都是html的字符数据
    2. 把一个文件变成字节数组，使用response.getOutputStream()来响应给浏览器
    
2. 下载的要求
    1. 两个头一个流
        1. Content-Type：你传递给客户端的文件是什么MIME类型，例如：image/pjpeg
            1. 通过文件名称调用ServletContext的getMimeType()方法，得到MIME类型
        2. Content-Disposition：它的默认值为inline，表示在浏览器窗口中打开！`attrachment;filename=xxx` 
            1. 在filename=后面跟随的是显示在下载框中的文件名称！
        3. 一个流：要下载的文件数据！
            1. 自己new一个输入流即可！然后将输入流复制到response.getOutputStream()

# 下载的细节
1. 显示在下载框中的中文名称会出现乱码
    1. FireFox：Base64编码
    2. 其他大部分浏览器：使用URL编码
    
2. 通用方案： 
```java
filename = new String(filename.getByte("GBK"),"ISO-8859-1");
```

# 邮件相关的协议
1. 收发邮件
    1. 客户端1->(发)邮件服务器(收)->客户端2
    2. 常用的邮件服务器：163、126、qq、souhu、sina
    
2. 邮件协议概述
    1. SMTP：(Simple Mail Transfer Protocol，简单邮件传输协议)发邮件协议
    2. POP3：(Post Office Protocol Version 3)，邮件协议第3版，收邮件协议
    3. IMAP：(Internet Message Access Protocol，因特网消息访问协议)收发邮件协议
    
3. 邮件服务器名称
    1. smtp服务端口号为25，服务器名称为smtp.xxx.xxx
    2. pop3服务器的端口号为110，服务器名称为pop3.xxx.xxx。
    3. 例如：
        1. 163：smtp.163.com和pop3.163.com
        2. 126：smtp.126.com和pop3.126.com
        3. qq：smtp.qq.com和pop3.qq.com
        4. souhu：smtp.souhu.com和pop3.sohu.com
        5. sina：smtp.sina.com和pop3.souhu.com
        
        
# telnet发送邮件
1. 连接163的smtp服务器
`telnet smtp.163.com 25`

2. 与服务器打招呼
`ehlo yourname`

3. 发出登录请求
`auth login`
`334 dXNlcm5hbWU6(username)，经过base64编码`

4. 输入加密后的邮箱名
`ZGV2aW5raW5AMTYzLmNvbQ==`

5. 输入加密后的邮箱密码
`adlfkjaldkjflakdsjasdf==`
a2luZ29saXZlcjIzOTg=

6. 发送邮件
```
mail from:<xxx@xxx.com>
rcpt to:<xxx@xxx.com>
from:
to:
subject: 
空行
正文内容
.号结束
250 Mail OK queued as smtp11,D8CowADHTxZXntlatV_FPg--.35511S2 1524211435
```

# JavaMail
1. 是java提供的一组API，用来发送和接收邮件！

## 使用流程
1. 导包：
    1. main.jar
    2. actvition.jar
    
2. 核心类：
    1. Session：
        1. 如果你得到了它，表示已经与服务器连接上了，与Connection的作用相似！
        
        2. 得到Session，需要使用`Session.getInstance(Properties props, Authenticator auth)`方法
        ```java
        Properties props = new Properties();
        props.setProperty("mail.host","smtp.163.com");
        props.setProperty("mail.smtp.auth", true);
        ```
        
        3. 实现Authenticator.getPasswordAuthentication()方法
        ```java
       Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication("itcast_cxf", "itcast");
           }       
        }
        Session session = Session.getInstance(props, auth);
        ```
        
    2. MimeMessage：
        1. 它表示一个邮件对象，你可以调用它setFrom()之类的方法，设置发件人，设置收件人、设置主题、设置正文！
        
    3. TransPort
        1. 它只有一个功能，发邮件！
        
## 发送带有附件的邮件
1. MimeMultipart类
    1. 类似集合，有如下方法 void addBodyPart(MimeBodyPart part);
2. MimeBodyPart类
    1. 设置主体方法
        1. setContent
    2. 设置附件相关方法
        1. attachFile()
        2. setFileName()

3. 例:
```java
/**
  * 当发送包含附件的邮件时，邮件体就为多部件形式！
  * 1. 创建一个多部件的部件内容
  *MimeMultipart就是一个集合，用来装载多个主体部件！
  * 2. 我们需要创建两个主体部件，一个是文本内容的，另一个是附件的。
  *主体部件叫MimeBodyPart
  * 3. 把MimeMultipart设置给MimeMessage的内容！
  */
 MimeMultipart msgList = new MimeMultipart();
 //创建MimeBodyPart
 MimeBodyPart part1 = new MimeBodyPart();
 //设置主体部件的内容
 part1.setContent("这是一封包含附件的垃圾邮件", "text/html;charset=utf-8");
 //把主体部件添加到集合中
 msgList.addBodyPart(part1);

 //创建MimeBodyPart
 MimeBodyPart part2 = new MimeBodyPart();
 part2.attachFile(new File("/home/king/test.jpeg"));
 //设置显示的文件名称，其中encodeText用来处理中文乱码问题
 part2.setFileName(MimeUtility.encodeText("垃圾电影.jpeg"));
 //把主体2部件添加到集合中
 msgList.addBodyPart(part2);
 //把它设置给邮件作为邮件的内容
 msg.setContent(msgList);
```


