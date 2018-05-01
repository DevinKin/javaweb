package cn.devinkin.bookstore.user.web.filter;

import cn.devinkin.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Login Filter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /**
         * 1. 从session中获取用户信息
         * 2. 判断session中存在用信息，放行
         * 3. 否则，保存错误信息，转发到login.jsp显示
         */
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        User user = (User) request.getSession().getAttribute("session_user");
        if (user != null) {
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("msg", "您还没有登录");
            request.getRequestDispatcher("/jsps/user/login.jsp").
                    forward(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
