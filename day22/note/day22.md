# 文件上传
## 一、文件上传对页面的要求
1. 必须使用表单，而不能是超链接
### 1、上传对表单的限制
1. 表单的method必须是POST，而不是GET
2. 表单的enctype必须是multipart/form-data,enctpye="multipart/form-data"
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
2. 解析器：ServletFIleUpload
3. 表单项：FileItem

### 2、步骤
1. 创建工厂：DiskFIleItemFactory factory = new DiskFileItemFactory();
2. 创建解析器：ServletFileUpload sfu = new ServletFileUpload(factory);
3. 使用解析器来解析request，得到FileItem集合：List<FileItem>  fileItemList = sfu.parseRequest(request);

### 3、FileItem的API
1. boolean isFormField()：是否为普通表单项 
2. String getFieldName()：返回当前表单项名称
3. String getString(String charset)：获取返回表单项的；
4. String getName()：返回上传的文件名称
5. long getSize()：返回上传文件的字节数
6. InputStream getInputStream()：返回上传文件对应的输入流 
7. void write(FIle destFile)：把上传的文件内容保存到指定的文件中。
8. String getCOntentType();