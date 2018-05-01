package cn.devinkin.bookstore.book.web.servlet.admin;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.book.service.BookService;
import cn.devinkin.bookstore.category.domain.Category;
import cn.devinkin.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    /**
     * 修改图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        category.setCname(categoryService.getCategoryName(category.getCid()));
        book.setCategory(category);
        bookService.edit(book);
        return findAll(request,response);
    }

    /**
     * 删除图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        bookService.delete(bid);
        return findAll(request,response);
    }

    /**
     * 添加图书前的工作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 查询所有分类，保存到request中，转发到/adminjsps/admin/book/add.jsp
         */
        request.setAttribute("categoryList", categoryService.findAll());
        return "f:/adminjsps/admin/book/add.jsp";
    }

    /**
     * 加载图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("book",bookService.findByBid(bid));
        request.setAttribute("categoryList", categoryService.findAll());
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    /**
     * 查看所有图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.findAll();
        request.setAttribute("bookList", bookList);
        return "f:/adminjsps/admin/book/list.jsp";
    }
}
