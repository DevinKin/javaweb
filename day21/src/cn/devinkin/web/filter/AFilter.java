package cn.devinkin.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class AFilter implements Filter{
    /**
     * 创建之后马上执行，用来初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器出生了");
    }

    /**
     * 每次过滤时都会执行
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AFilter#start");
        filterChain.doFilter(servletRequest, servletResponse); //放行！
        System.out.println("AFilter#end");
    }

    /**
     * 销毁之前执行，用来做对非内存资源进行释放
     */
    @Override
    public void destroy() {
        System.out.println("过滤器死了");
    }
}
