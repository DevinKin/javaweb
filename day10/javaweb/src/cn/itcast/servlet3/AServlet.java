package cn.itcast.servlet3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * 演示request获取请求参数
 */
public class AServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get: " + req.getParameter("xxx"));
        System.out.println("Get: " + req.getParameter("yyy"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobby = req.getParameterValues("hobby");

        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("hobby: " + Arrays.toString(hobby));

        /**
         * 测试所有请求参数的名称
         */
        Enumeration names = req.getParameterNames();
        while(names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }

        /**
         * 获取所有请求参数，封装到Map中
         */
        Map<String, String[]> map = req.getParameterMap();
        for (String name : map.keySet()) {
            String[] values = map.get(name);
            System.out.println(name + "=" + Arrays.toString(values));
        }
    }
}
