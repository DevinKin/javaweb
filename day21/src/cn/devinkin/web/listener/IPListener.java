package cn.devinkin.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class IPListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<String, Integer> ipMap = new LinkedHashMap<>();
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("ipMap", ipMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
