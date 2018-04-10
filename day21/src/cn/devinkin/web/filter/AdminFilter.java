package cn.devinkin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /**
         * 1. 得到session
         * 2. 判断session中域中是否存在admin，如果存在，放行
         * 3. 判断session域中是否存在username，如果存在，放行，否则打回login.jsp
         */
        HttpServletRequest request = (HttpServletRequest)req;
        String name = (String) request.getSession().getAttribute("admin");
        if (name != null) {
            chain.doFilter(req, resp);
            return;
        }
        else {
            req.setAttribute("msg", "你不是管理员，快滚");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
