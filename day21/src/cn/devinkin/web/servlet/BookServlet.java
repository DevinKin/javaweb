package cn.devinkin.web.servlet;

import cn.devinkin.web.service.BookService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    public String findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("bookList",bookService.findAll());
        return "f:/show.jsp";
    }

    public String findByCategory(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String value = req.getParameter("cid");
        int category = Integer.parseInt(value);
        req.setAttribute("bookList",bookService.findByCategory(category));
        return "f:/show.jsp";
    }

}
