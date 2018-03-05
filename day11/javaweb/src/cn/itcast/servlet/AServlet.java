package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AServlet")
public class AServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String s1 = request.getParameter("num1");
        String s2 = request.getParameter("num2");

        //2.把字符串转换成int类型
        int n1 = Integer.parseInt(s1);
        int n2 = Integer.parseInt(s2);

        //3.进行加法运算得到结果
        int sum = n1 + n2;

        //4.保存结果到request域中
        request.setAttribute("result", sum);

        //5.转发到result.jsp
        request.getRequestDispatcher("/jia/result.jsp").forward(request,response);
    }
}
