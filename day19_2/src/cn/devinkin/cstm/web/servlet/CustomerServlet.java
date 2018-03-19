package cn.devinkin.cstm.web.servlet;

import cn.devinkin.cstm.domain.Customer;
import cn.devinkin.cstm.service.CustomerService;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Web层
 * @author king
 */
public class CustomerServlet extends BaseServlet {
    private CustomerService customerService = new CustomerService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    /**
     * 添加用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 封装表单数据到Customer对象中
         * 2. 补全：cid，使用uuid
         * 3. 使用service方法完成添加工作
         * 4. 向request域中保存成功信息
         * 5. 转发msg.jsp
         */
        Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
        c.setCid(CommonUtils.uuid());
        customerService.add(c);
        req.setAttribute("msg", "恭喜，添加成功");
        return "f:/jsp/msg.jsp";
    }

    /**
     * 查询所有
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 调用service得到所有客户
         * 2. 保存到request域中
         * 3. 转发到list.jsp
         */
        req.setAttribute("cstmList", customerService.findAll());
        return "f:/jsp/cstm/list.jsp";
    }

    /**
     * 编辑之前的加载工作
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String preEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 获取cid
         * 2. 使用cid来调用service方法，得到Customer对象
         * 3. 把Customer保存到request域中
         * 4. 转发edit.jsp显示在表单中
         */
        String cid = req.getParameter("cid");
        Customer cstm = customerService.load(cid);
        req.setAttribute("cstm",cstm);
        return "f:/jsp/cstm/edit.jsp";
    }

    public String edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 封装表单数据到Customer对象中
         * 2. 调用service方法完成修改
         * 3. 保存成功信息到request域中
         * 4. 转发到msg.jsp显示成功信息
         */
        // 已经封装了cid到Customer对象中
        Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
        customerService.edit(c);
        req.setAttribute("msg", "恭喜，编辑用户成功！");
        return "f:/jsp/msg.jsp";
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 获取表单数据中的cid
         * 2. 将cid传递给CustomerService#delete方法
         * 3. 保存成功信息到request域中
         * 4. 转发到msg.jsp显示成功信息
         */
        String cid = req.getParameter("cid");
        customerService.delete(cid);
        req.setAttribute("msg", "恭喜，删除用户成功！");
        return "f:/jsp/msg.jsp";
    }

    public String query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. 封装表单数据到Customer中(cname,gender,cellphone,email)
         * 2. 调用service方法，传递条件(Customer对象)，返回查询结果List<Customer>
         * 3. 把结果保存到request域中
         * 4. 转发到list.jsp页面中显示
         */
        Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
        List<Customer> cstmList = customerService.query(c);
        req.setAttribute("cstmList", cstmList);
        return "f:/jsp/cstm/list.jsp";
    }
}
