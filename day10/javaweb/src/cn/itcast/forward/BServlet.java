package cn.itcast.forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BServlet4...");
        System.out.println(req.getAttribute("username"));
        resp.setHeader("bbb","BBB");
        resp.getWriter().println("hello BServlet4");
    }
}
