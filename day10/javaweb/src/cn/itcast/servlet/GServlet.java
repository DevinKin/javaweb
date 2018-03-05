package cn.itcast.servlet;

import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "GServlet")
public class GServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
        * 使用ServletOutputStream流发送字节数据
        */
        /*
        String s = "Hello outputStream";
        byte[] bytes = s.getBytes();
        response.getOutputStream().write(bytes);
        */


        /**
         * 演示响应字节数据
         */
        //把一张照片读取到字节数组中
        String path = "/home/king/picture/tool1.png";
        FileInputStream in = new FileInputStream(path);
        //读取输入流内容的字节到字节数组中
        byte[] bytes = IOUtils.toByteArray(in);
        response.getOutputStream().write(bytes);

    }
}
