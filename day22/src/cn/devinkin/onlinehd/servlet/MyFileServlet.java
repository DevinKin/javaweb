package cn.devinkin.onlinehd.servlet;

import cn.devinkin.onlinehd.domain.MyFile;
import cn.devinkin.onlinehd.service.MyFileService;
import cn.devinkin.web.down.utils.DownUtils;
import cn.itcast.servlet.BaseServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@WebServlet(name = "MyFileServlet")
public class MyFileServlet extends BaseServlet {
    private MyFileService myFileService = new MyFileService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 1. 调用myFileService#findAll方法得到List<MyFile>
         * 2. 将List<MyFile>保存到request域中
         * 3. 转发到/onlinehd/list.jsp
         */
        List<MyFile> list = myFileService.findAll();
        request.setAttribute("myFilelist", list);
        return "f:/onlinehd/list.jsp";
    }

    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 1. 获取list.jsp请求的fid参数
         * 2. 调用service#download方法获取myFile对象
         * 3. 通过myFile#getFilepath方法获取文件路径
         * 4. 设置两个头一个流，Content-Type,Content-Disposition,response.getOutputStream()
         */
        //1.
        String fid = request.getParameter("fid");
        //2.
        MyFile myFile = myFileService.download(fid);
        //3.
        String filePath = myFile.getFilepath();

        //4.
        InputStream in = new FileInputStream(new File(filePath));
        String contentType = this.getServletContext().getMimeType(filePath);
        //设置下载文件时候编码
        String frameName = DownUtils.filenameEncoding(myFile.getFramename(), request);
        String contentDispositon = "attachment;filename=" + frameName;

        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", contentDispositon);
        IOUtils.copy(in, response.getOutputStream());
        return null;
    }
}
