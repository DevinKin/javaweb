package cn.devinkin.first.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BServlet")
public class BServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xml = "<students>" +
                "<student number='ITCAST_1001'>" +
                "<name>zhangsan</name>" +
                "<age>18</age>" +
                "<sex>male</sex>" +
                "</student>" +
                "</students>";
        response.setContentType("text/xml;charset=utf-8");
        response.getWriter().print(xml);

    }
}
