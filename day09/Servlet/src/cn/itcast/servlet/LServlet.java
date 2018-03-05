package cn.itcast.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 演示获取类路径下的资源
 * @author devinkin
 */
public class LServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 得到classLoader
         *  - 先得到Class,再得到ClassLoader
         * 2. 调用其getResourceAsStream(),得到一个InputStream
         * 3.
         */
        //相当于src目录,相对/classes
        //ClassLoader cl = this.getClass().getClassLoader();
        //InputStream input = cl.getResourceAsStream("cn/itcast/a.txt");

        //相当于当前LServlet.class所在目录
        Class c = this.getClass();
        //InputStream input = c.getResourceAsStream("a.txt");

        // 相对classes下
        //InputStream input = c.getResourceAsStream("/a.txt");

        // 获取index.jsp文件
        InputStream input = c.getResourceAsStream("/../../index.jsp");

        String s = IOUtils.toString(input);
        System.out.println(s);
    }
}
