package cn.devinkin.first.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DServlet")
public class DServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 向客户端发送json串
         */
        String str = "{\"name\":\"zhangSan\", \"age\":18,\"sex\":\"male\"}";
        response.getWriter().print(str);
        System.out.println(str);
    }
}
