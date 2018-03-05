package cn.itcast.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 通过request来获取url的相关方法
         */
        //获取请求协议
        resp.getWriter().print(req.getScheme() + "\n");
        //获取ServerName
        resp.getWriter().print(req.getServerName() + "\n");
        //获取ServerPort
        resp.getWriter().print(req.getServerPort() + "\n");
        //获取项目名
        resp.getWriter().print(req.getContextPath() + "\n");
        //获取Servlet路径名
        resp.getWriter().print(req.getServletPath() + "\n");
        //获取参数部分
        resp.getWriter().print(req.getQueryString() + "\n");
        //获取请求URI
        resp.getWriter().print(req.getRequestURI() + "\n");
        //获取请求URL
        resp.getWriter().print(req.getRequestURL() + "\n");

    }
}
