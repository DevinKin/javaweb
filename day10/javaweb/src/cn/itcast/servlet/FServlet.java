package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 禁用浏览器缓存
 * @author devinkin
 */
@WebServlet(name = "FServlet")
public class FServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Cache-Control、pragma、expires
         */
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("pragma","no-cache");
        response.setDateHeader("expires",-1);
        response.getWriter().print("Hello world!");
    }
}
