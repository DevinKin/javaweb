package cn.devinkin.cstm.web.servlet;

import cn.devinkin.cstm.domain.Customer;
import cn.devinkin.cstm.domain.PageBean;
import cn.devinkin.cstm.service.CustomerService;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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


/*    *//**
     * 查询所有
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     *//*
    public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        *//**
         * 1. 调用service得到所有客户
         * 2. 保存到request域中
         * 3. 转发到list.jsp
         *//*
        req.setAttribute("cstmList", customerService.findAll());
        return "f:/jsp/cstm/list.jsp";
    }
    */

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取页面传递的pc
         * 2. 给定ps的值
         * 3. 使用pc和ps的值调用service方法得到PageBean,保存到request域
         * 4. 转发到list.jsp
         */

        //1. 得到pc
        int pc = getPc(request);

        //2. 给定ps的值，每页10行记录
        int ps = 10;

        //3. 使用pc和ps的值调用service方法得到PageBean,保存到request域
        PageBean<Customer> pb = customerService.findAll(pc, ps);

        // 保存url
        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        //转发到list.jsp
        return "f:/jsp/cstm/list.jsp";
    }

    /**
     * 获取pc
     * @param request
     * @return
     */
    private int getPc(HttpServletRequest request) {
        /**
         * 1. 得到pc
         *  如果pc参数不存在，说明pc=1
         *  如果pc参数存在，需要转换成int类型即可
         */
        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(value);
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
         * 1. 把条件封装到Customer对象中
         * 2. 获取页面pc
         * 3. 给定ps的值
         * 4. 使用pc和ps的值，以及提哦啊件对象,调用service方法得到PageBean，保存到request域中
         * 5. 转发到list.jsp
         */
        //获取查询条件
        Customer criteria = CommonUtils.toBean(req.getParameterMap(), Customer.class);

        /**
         * 处理GET请求编码问题！
         */
        //criteria = encoding(criteria);

        int pc = getPc(req);
        int ps = 10;
        PageBean<Customer> pb = customerService.query(criteria, pc, ps);

        //得到url，保存到pb中
        pb.setUrl(getUrl(req));

        req.setAttribute("pb",pb);
        return "f:/jsp/cstm/list.jsp";
    }

    /**
     * 处理四样数据
     * @param criteria
     * @return
     */
    private Customer encoding(Customer criteria) throws UnsupportedEncodingException {
        String cname = criteria.getCname();
        String gender = criteria.getGender();
        String cellphone = criteria.getCellphone();
        String email = criteria.getEmail();

        if (cname != null && !cname.trim().isEmpty()) {
            cname = new String(cname.getBytes("ISO-8859-1"), "utf-8");
            criteria.setCname(cname);
        }
        if (gender != null && !gender.trim().isEmpty()) {
            gender = new String(gender.getBytes("ISO-8859-1"), "utf-8");
            criteria.setGender(gender);
        }
        if (cellphone != null && !cellphone.trim().isEmpty()) {
            cellphone = new String(cellphone.getBytes("ISO-8859-1"), "utf-8");
            criteria.setCellphone(cellphone);
        }
        if (email != null && !email.trim().isEmpty()) {
            email = new String(email.getBytes("ISO-8859-1"), "utf-8");
            criteria.setEmail(email);
        }
        return criteria;
    }

    /**
     * 截取url
     *  /项目名/Servlet路径?参数字符串
     * @param request
     * @return
     */
    private String getUrl(HttpServletRequest request) {
        //截取项目名
        String contextPath = request.getContextPath();
        //截取servlet路径
        String servletPath = request.getServletPath();
        //截取参数
        String queryString = request.getQueryString();

        //判断参数部分中是否包含pc这个参数，如果包含，需要截取下去，不要这一个部分
        if (queryString.contains("&pc=")) {
            int index = queryString.lastIndexOf("&pc=");
            queryString = queryString.substring(0, index);
        }
        return contextPath + servletPath + "?" + queryString;
    }
}
