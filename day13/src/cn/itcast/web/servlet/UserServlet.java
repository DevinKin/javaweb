package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 在servlet中依赖service,然后通过service完成功能,把结果保存到request
         * 然后转发到jsp中
         */
        UserService userService = new UserService();
        User user = userService.find();
        request.setAttribute("user", user);

        request.getRequestDispatcher("/service/show.jsp").forward(request,response);
    }
}
