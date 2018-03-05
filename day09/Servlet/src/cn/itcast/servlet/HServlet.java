package cn.itcast.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示从Servlet中获取数据
 */
public class HServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 获取Servlet对象
         * 2. 调用getAttribute()方法完成获取对象
         */
        ServletContext application = this.getServletContext();
        String name = (String)application.getAttribute("name");
        System.out.println(name);
    }
}
