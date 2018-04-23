package cn.devinkin.first.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Hello AJAX!");
        response.getWriter().print("Hello AJAX!!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        System.out.println("(POST:) Hello AJAX!" + username);
        resp.getWriter().print("Hello AJAX!!!" + username);
    }
}
