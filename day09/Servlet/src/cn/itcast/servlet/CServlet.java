package cn.itcast.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CServlet extends BServlet{
    @Override
    public void init() {
        System.out.println("CServlet");
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        String value = getInitParameter("p1");
    }
}
