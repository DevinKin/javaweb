package cn.itcast.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.domain.User;
import cn.itcast.service.UserException;
import cn.itcast.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginServlet层
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理请求编码(POST)
        request.setCharacterEncoding("utf-8");
        //处理响应编码
        response.setContentType("text/html;charset=utf-8");

        //依赖UserService
        UserService userService = new UserService();

        /**
         * 1.封装表单数据到User form中
         * 2.调用service的login()方法,得到返回User user对象
         *   > 如果抛出异常,获取异常信息,保存到request域中,保存form,转发到login.jsp
         *   > 如果没有抛出异常,保存User user对象到session中,重定向到welcome.jsp
         */
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);

        try {
            User user = userService.login(form);
            request.getSession().setAttribute("sessionUser", user);
            response.sendRedirect(request.getContextPath() + "/user/welcome.jsp");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user", form);
            request.getRequestDispatcher("user/login.jsp").forward(request,response);
        }

    }

}
