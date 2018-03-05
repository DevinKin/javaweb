package cn.itcast.include;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AServlet5...");
        /**
         * 演示请求包含
         */
        //设置请求头
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("aaa", "AAA");
        //设置请求体
        resp.getWriter().print("我是aaaaaaaaa");

        //请求包含
        req.getRequestDispatcher("/BServlet5").include(req,resp);
    }
}
