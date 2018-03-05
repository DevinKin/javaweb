package cn.itcast.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class DServlet extends GenericServlet{
    @Override
    public void init() {
        System.out.println("I come");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello world");
    }

    @Override
    public void destroy() {
        System.out.println("I die");
        super.destroy();
    }
}
