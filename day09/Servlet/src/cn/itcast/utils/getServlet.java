package cn.itcast.utils;

import cn.itcast.utils.Dom4jUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import java.lang.reflect.Method;
import java.util.List;

public class getServlet {
    @Test
    public Servlet getServlet() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        Element root = document.getRootElement();
        List<Element> list = root.elements("servlet");
        String servletName = "";

        for (Element ele : list) {
            String name = ele.element("servlet-name").getText();
            if (name.equals("XXX")) {
                servletName = ele.element("servlet-class").getText();
                break;
            }
        }
        System.out.println(servletName);
        Class servletClass = Class.forName(servletName);
        Servlet servlet = (Servlet) servletClass.newInstance();
        //Method init = servletClass.getDeclaredMethod("init", ServletConfig.class);
        //init.invoke(servlet, null);
        return servlet;
    }
}
