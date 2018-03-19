package cn.devinkin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BServlet")
public class BServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String func1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("func1()...");
        return "f:/index.jsp";
    }

    public String func2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("func2()...");
        return "r:/index.jsp";
    }

    public String func3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("func3()...");
        return "d:/WEB-INF/files/a.jpg";
    }
}
