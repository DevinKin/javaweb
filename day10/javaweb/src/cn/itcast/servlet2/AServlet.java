package cn.itcast.servlet2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示: 获取客户端的IP地址,获取请求方式,获取User-Agent,得到客户端信息:OS和浏览器信息
 */
@WebServlet(name = "AServlet")
public class AServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取IP地址
        String addr = request.getRemoteAddr();
        System.out.println(addr);

        // 获取请求方式
        System.out.println("请求方式: " + request.getMethod());

        //获取User-Agent
        String userAgent = request.getHeader("User-Agent");
        System.out.println("User-Agent: " + userAgent);
        if (userAgent.toLowerCase().contains("chrome")) {
            System.out.println("你好: " + addr + ", 你用的是谷歌");
        } else if (userAgent.toLowerCase().contains("firefox")) {
            System.out.println("你好: " + addr + ", 你用的是火狐");
        } else if (userAgent.toLowerCase().contains("msie")) {
            System.out.println("你好: " + addr + ", 你用的是ie");
        }
    }
}
