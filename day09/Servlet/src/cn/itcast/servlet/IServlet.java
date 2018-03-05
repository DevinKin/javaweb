package cn.itcast.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示ServletContext获取公共的初始化参数
 * @author devinkin
 */
public class IServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 得到ServletContext
         * 2. 调用它getInitParameter(String)得到初始化参数
         */
        ServletContext app = this.getServletContext();
        String value = app.getInitParameter("context-param");
        System.out.println(value);
    }
}
