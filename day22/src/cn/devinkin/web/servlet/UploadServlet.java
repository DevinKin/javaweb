package cn.devinkin.web.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

public class UploadServlet extends javax.servlet.http.HttpServlet {
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ServletInputStream in = request.getInputStream();
        String s = IOUtils.toString(in);
        System.out.println(s);
    }

    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
