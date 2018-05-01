package cn.devinkin.bookstore.user.web.servlet;

import cn.devinkin.bookstore.user.dao.AdminService;
import cn.devinkin.bookstore.user.domain.Admin;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends BaseServlet {
    private AdminService adminService = new AdminService();
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = CommonUtils.toBean(request.getParameterMap(), Admin.class);
        Admin cadmin = adminService.findByUsername(admin.getAdminname());
        if (cadmin == null) {
            request.setAttribute("msg", "用户不存在");
            return "f:/adminjsps/login.jsp";
        } else if (!cadmin.getPassword().equals(admin.getPassword())) {
            request.setAttribute("msg", "密码错误");
            return "f:/adminjsps/login.jsp";
        }
        request.getSession().setAttribute("session_admin",admin);
        //这里应该是重定向而不是转发
        return "r:/adminjsps/admin/index.jsp";
    }

}
