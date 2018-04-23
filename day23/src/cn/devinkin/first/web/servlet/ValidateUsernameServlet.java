package cn.devinkin.first.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /**
         * 1. 获取参数username
         * 2. 判断是否为itcast
         *  1. 如果是，响应1
         *  2. 如果否，响应0
         */
        String username = request.getParameter("username");
        if (username.equals("itcast")) {
            response.getWriter().print("1");
        } else {
            response.getWriter().print("0");
        }

    }

}
