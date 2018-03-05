package cn.itcast.servlet;

import javax.servlet.*;
import java.util.Enumeration;

/**
 * 查看Servlet接口中的方法
 */
public class AServlet implements Servlet{
    /**
     * 可以用来获取Servlet的配置信息
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig()...");
        return null;
    }


    /**
     * 它是生命周期方法
     * 它会在Servlet对象创建之后马上执行,并执行一次!(出生之后)
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init()...");
        System.out.println(config.getServletName());
        /**
         * 获取初始化参数
         */
        System.out.println(config.getInitParameter("p1"));
        System.out.println(config.getInitParameter("p2"));

        /**
         * 获取所有初始化参数的名称
         */
        Enumeration e = config.getInitParameterNames();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }



    /**
     * 它是生命周期方法
     * 它会被调用多次!!!
     * 每次处理请求都是调用这个方法!
     * @param servletRequest
     * @param servletResponse
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("service()...");
    }


    /**
     * 获取Servlet的信息
     * @return
     */
    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo()...");
        return null;
    }


    /**
     * 它是生命周期方法
     * 它会在Servlet被销毁之前调用,并且只会被调用一次!
     */
    @Override
    public void destroy() {
        System.out.println("destory()....");
    }
}
