package cn.itcast.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 模拟GenericServlet
 * @author DevinKin
 */
public class BServlet implements Servlet{


    private ServletConfig servletConfig;



    /**
     * 这个方法是本类自己定义的,不要Servlet接口中的方法
     */
    public void init() {
        System.out.println("I am BServlet");
    }

    /**
     * 由Tomcat来调用,并且只调用一次
     * 它是这些方法中第一个被调用的，它会在构造器之后马上被调用
     * @param servletConfig
     */
    @Override
    public void init(ServletConfig servletConfig) {
        //把tomcat传递的ServletConfig赋值给本类的一个成员，其实就是把它保存起来,方便其他方法使用
        this.servletConfig = servletConfig;
        init();
    }

    /**
     * 没有用的东西,可实现可不实现
     * @return
     */
    @Override
    public String getServletInfo() {
        return "servlet";
    }


    @Override
    public ServletConfig getServletConfig() {
        //init()被调用后，本类的成员this.servletConfig已经有值了
        return this.servletConfig;
    }

    /**
     * 需要就写，不需要就不谢
     * @return
     */
    @Override
    public void destroy() {
        System.out.println("die....");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("service...");
    }

    public ServletContext getServletContext() {
        return servletConfig.getServletContext();
    }

    public String getServletName() {
        return servletConfig.getServletName();
    }

    public String getInitParameter(String name) {
        return servletConfig.getInitParameter(name);
    }

    public Enumeration<String> getInitParameterNames() {
        return servletConfig.getInitParameterNames();
    }
}
