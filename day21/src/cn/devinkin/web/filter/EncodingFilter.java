package cn.devinkin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理post请求编码问题
        req.setCharacterEncoding("utf-8");

        HttpServletRequest request = (HttpServletRequest)req;
        /**
         * 处理GET请求的编码问题
         */
//        String username=req.getParameter("username");
//        username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
        /**
         * 调包request
         * 1. 写一个request的装饰类
         * 2. 在放行时使用我们自己的request
         */
        if (request.getMethod().equals("GET")){
            EncodingRequest er = new EncodingRequest(request);
            chain.doFilter(er, resp);
        } else if (request.getMethod().equals("POST")) {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
