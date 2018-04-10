package cn.devinkin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
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
        name = (String) request.getSession().getAttribute("user");
        System.out.println("userfilter: " + name);
        if (name != null) {
            chain.doFilter(req,resp);
            return;
        }
        else {
            req.setAttribute("msg", "你什么都不是，不要瞎折腾");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
