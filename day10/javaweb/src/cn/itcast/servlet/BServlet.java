package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示重定向!
 * @author devinkin
 *
 * 用户请求BServlet,然后BServlet响应302,,给出Location头
 */
@WebServlet(name = "BServlet")
public class BServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BServlet");

        /**
         * 重定向:
         * 1. 设置Location
         * 2. 发送302状态吗
         */
//        response.setHeader("Location", "/CServlet");
//        response.setStatus(302);

        //快捷的重定向方法
        response.sendRedirect("/CServlet");
    }
}
