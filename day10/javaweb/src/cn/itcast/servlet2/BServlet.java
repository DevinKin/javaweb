package cn.itcast.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 使用Referer请求头,来防盗链
         */
        String referer = req.getHeader("Referer");
        System.out.println(referer);
        if (referer == null || referer.contains("localhost")) {
            System.out.println("you are my client!");
        }
        else {
            resp.sendError(404,"就是不让你看!");
        }
    }
}
