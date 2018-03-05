package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;


/**
 * 使用ServletContext获取资源路径
 * @author devinkin
 */
@WebServlet(name = "JServlet")
public class JServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 它得到的是有盘符的路径: /home/share/xxx/xxx/xxx/
         */
        String path = this.getServletContext().getRealPath("/index.jsp");
        System.out.println(path);

        InputStream inputStream = this.getServletContext().getResourceAsStream(path);

        Set paths = this.getServletContext().getResourcePaths("/WEB-INF");
        System.out.println(paths);
    }
}
