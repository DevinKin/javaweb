package cn.devinkin.bookstore.order.web.servlet;

import cn.devinkin.bookstore.order.domain.Order;
import cn.devinkin.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();

    /**
     * 通过订单状态查找订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findOrderByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String st = request.getParameter("state");
        int state = Integer.parseInt(st);
        List<Order> orderList = orderService.findOrderByState(state);
        request.setAttribute("orderList", orderList);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    /**
     * 查找所有订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 获取List<Order>，保存到request域中
         * 转发到/adminjsps/admin/order/list.jsp
         */
        List<Order> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "f:/adminjsps/admin/order/list.jsp";
    }

    public String delivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        orderService.updateState(oid, 3);
        return findAll(request,response);
    }
}
