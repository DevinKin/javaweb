package cn.devinkin.web.servlet;

import cn.devinkin.web.down.utils.DownUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 两个头一个流
         * 1. Content-Type
         * 2. Content-Disposition
         * 3. 流：下载文件的数据
         */
        //两个头
        String filename = "/home/king/Music/CloudMusic/蔡健雅 - 红色高跟鞋.mp3";
        //为了使下载框中显示中文名称不出乱码！
        int pos = filename.lastIndexOf("/");
//        String framename = new String(filename.substring(pos+1).getBytes("GBK"),"ISO-8859-1");
        String framename = DownUtils.filenameEncoding(filename.substring(pos+1),request);
        //通过文件名称获取MIME类型
        String contentType = this.getServletContext().getMimeType(filename);

        String contentDisposition = "attachment;filename=" + framename;
        //一个流
        FileInputStream input = new FileInputStream(filename);

        //设置头
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", contentDisposition);

        //获取绑定了客户端的流
        ServletOutputStream output = response.getOutputStream();
        //把输入流的数据写入到输出流
        IOUtils.copy(input, output);
        input.close();
    }
}
