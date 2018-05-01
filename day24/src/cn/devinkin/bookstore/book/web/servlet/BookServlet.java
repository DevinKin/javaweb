package cn.devinkin.bookstore.book.web.servlet;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookService();

    /**
     * 查询图书详情
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book", book);
        return "f:/jsps/book/desc.jsp";
    }

    /**
     * 查询所有图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList", bookService.findAll());
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 通过分类查询图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("bookList", bookService.findByCategory(cid));
        return "f:/jsps/book/list.jsp";
    }
}
