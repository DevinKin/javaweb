package cn.itcast.servlet4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 先获取来使用iso的错误字符串
         * 2. 使用iso回退，使用utf-8重编
         */
        String name = req.getParameter("username");
        byte[] bytes = name.getBytes("ISO-8859-1");
        name = new String(bytes, "UTF-8");
        System.out.println(name);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(name);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 在获取参数之前，需要先调用request.setCharacterEncoding("utf-8");
         * 2. 使用getParameter()来获取参数
         */
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("username");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(name);
    }
}
