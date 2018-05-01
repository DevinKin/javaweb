package cn.devinkin.bookstore.category.admin.web.servlet;

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

@WebServlet(name = "AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();


    /**
     * 修改分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 封装表单数据
         * 2. 调用service方法完成修改工作
         * 3. 调用findAll()
         */
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        categoryService.edit(category);
        return findAll(request,response);
    }

    /**
     * 修改之前的加载工作
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("category", categoryService.load(cid));
        return "f:/adminjsps/admin/category/mod.jsp";
    }

    /**
     * 删除分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取参数cid
         * 2. 调用service方法传递cid参数
         *  1. 如果抛出异常，保存异常信息，转发到msg.jsp显示
         * 3. 调用findAll()
         */
        String cid = request.getParameter("cid");
        try {
            categoryService.delete(cid);
        } catch (CategoryException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/adminjsps/admin/msg.jsp";
        }
        return findAll(request,response);
    }

    /**
     * 添加分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 补全表单数据
         * 2. 补全cid
         * 3. 调用service方法完成添加工作
         * 4. 调用findAll()
         */
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        category.setCid(CommonUtils.uuid());

        categoryService.add(category);
        return findAll(request,response);
    }

    /**
     * 查找所有分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 调用service方法，得到所有分类
         * 2. 保存到request域，转发到/adminjsps/admin/category/list.jsp
         */
        request.setAttribute("categoryList", categoryService.findAll());
        return "f:/adminjsps/admin/category/list.jsp";
    }
}
