package cn.devinkin.bookstore.cart.web.servlet;

import cn.devinkin.bookstore.book.domain.Book;
import cn.devinkin.bookstore.book.service.BookService;
import cn.devinkin.bookstore.cart.domain.Cart;
import cn.devinkin.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookService();

    /**
     * 添加购物车条目
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //登录时获取车
        Cart cart  = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //获取bid、count参数
        String bid = request.getParameter("bid");
        String count = request.getParameter("count");
        //通过bid得到book对象
        Book book = bookService.load(bid);
        //使用book和数量来创建CartItem
        CartItem cartItem = new CartItem();
        cartItem.setCount(Integer.parseInt(count));
        cartItem.setBook(book);

        //把CartItem添加到车
        cart.add(cartItem);

        return "f:/jsps/cart/list.jsp";
    }

    /**
     * 清空购物车条目
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clear();
        return "f:/jsps/cart/list.jsp";
    }

    /**
     * 删除购物车条目
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.delete(bid);
        return "f:/jsps/cart/list.jsp";
    }
}
