package cn.devinkin.bookstore.user.web.filter;

import cn.devinkin.bookstore.user.domain.Admin;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    private QueryRunner qr = new TxQueryRunner();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Admin admin = (Admin) request.getSession().getAttribute("session_admin");
        if (admin != null) {
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("msg", "您还没有登录");
            request.getRequestDispatcher("/adminjsps/login.jsp").
                    forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
