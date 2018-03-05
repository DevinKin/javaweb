package cn.itcast.forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * 演示请求转发
         */
        System.out.println("AServlet4....");
        //设置响应头
        resp.setHeader("aaa","AAA");
        //设置响应体,无法输出
        resp.getWriter().print("hello AServlet4!");
//        for (int i = 0; i < 1024 * 24 + 1; i++) {
//            resp.getWriter().print("a");
//        }

        /**
         * 向request域中添加一个属性
         */
        req.setAttribute("username", "zhangsan");
        //请求转发,等同于调用BServlet4的service()方法
        req.getRequestDispatcher("/BServlet4").forward(req, resp);

    }
}
