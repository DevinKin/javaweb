package cn.itcast.include;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BServlet5...");
        //设置响应头
        resp.setHeader("bbb","BBB");
        //设置响应体
        resp.getWriter().print("bbbbbbbbbbbbbbbb");
    }
}
