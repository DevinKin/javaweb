package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 演示定时刷新
 * @author devinkin
 *
 * 设置一个Refresh,它表示定时刷新!
 *
 */
public class DServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 下面是用来发送响应体!
         */
        PrintWriter writer = resp.getWriter();
        writer.print("欢迎xxx登录!5秒钟后自动跳转到主页!");
        /**
         * 设置名为Refresh的响应头
         */
        resp.setHeader("Refresh", "5;URL=/EServlet");
    }
}
