package cn.devinkin.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取用户名
         * 2. 判断用户名中是否包含devinkin
         * 3. 如果包含，就是管理员
         * 4. 如果不包含，就是普通会员
         * 5. 要把登录的用户名称保存到session中
         */
        String username = request.getParameter("username");
        if (username.contains("devinkin")) {
            request.getSession().setAttribute("admin", username);
        } else if (!username.trim().isEmpty()){
            request.getSession().setAttribute("user", username);
        }
        request.getRequestDispatcher("/youke.jsp").forward(request, response);
    }
}
