package cn.itcast.customer.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.customer.dao.CustomerDao;
import cn.itcast.customer.domain.Customer;
import cn.itcast.customer.domain.PageBean;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

/**
 * CustomerServlet
 * @author qdmmy6
 *
 */
public class CustomerServlet extends BaseServlet {
	private CustomerDao customerDao = new CustomerDao();
	/**
	 * 添加客户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取表单数据，封装到Customer中
		 * 2. 为Customer指定cid
		 * 3. 调用dao完成添加
		 * 4. 在request中添加成功信息
		 * 5. 转发到msg.jsp显示
		 */
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customer.setCid(CommonUtils.uuid());
		customerDao.add(customer);
		request.setAttribute("msg", "添加客户成功！");
		return "f:/jsp/msg.jsp";
	}

	/**
	 * 编辑客户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取表单数据，封装到Customer中
		 * 2. 调用dao完成编辑
		 * 3. 在request中保存成功信息
		 * 4. 转发到msg.jsp
		 */
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customerDao.update(customer);
		request.setAttribute("msg", "编辑客户成功！");
		return "f:/jsp/msg.jsp";
	}
	
	/**
	 * 编辑前的加载
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadForEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 * 2. 调用dao的加载方法得到Customer
		 * 3. 把Customer保存到request中
		 * 4. 转发到edit.jsp显示
		 */
		String cid = request.getParameter("cid");
		Customer customer = customerDao.load(cid);
		request.setAttribute("customer", customer);
		return "f:/jsp/cstm/edit.jsp";
	}
	
	/**
	 * 删除客户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 * 2. 调用dao完成删除
		 * 3. 保存成功信息到request中
		 * 4. 转发到msg.jsp显示
		 */
		String cid = request.getParameter("cid");
		customerDao.delete(cid);
		request.setAttribute("msg", "删除客户成功！");
		return "f:/jsp/msg.jsp";
	}
	
	/**
	 * 查看客户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取pc，如果pc不存在，设置pc=1
		 * 2. 获取url，如果url中包含了&pc=，那么切除它
		 * 3. 获取tr
		 * 4. 设置ps
		 * 5. 创建PageBean
		 * 6. 获取datas
		 * 7. 保存PageBean到request中
		 * 8. 转发到list.jsp
		 */
		/*
		 * 1. 获取pc，即当前页码
		 */
		String pageCode = request.getParameter("pc");
		int pc = 1;
		if(pageCode != null && !pageCode.trim().isEmpty()) {
			pc = Integer.parseInt(pageCode);
		}
		
		/*
		 * 2. 获取url，即请求路径及参数
		 */
		String url = request.getRequestURI() + "?" + request.getQueryString();
		int lastIndex = url.lastIndexOf("&pc=");
		if(lastIndex != -1) {
			url = url.substring(0, lastIndex);
		}
		
		/*
		 * 3. 获取tr，即总记录数
		 */
		int tr = customerDao.countFindAll();
		
		/*
		 * 4. 设置ps，即每页记录数
		 */
		int ps = 10;
		
		/*
		 * 5. 创建PageBean
		 */
		PageBean<Customer> pb = new PageBean<Customer>(pc, tr, ps);
		pb.setUrl(url);
		
		/*
		 * 6. 获取datas，即当前页记录
		 */
		List<Customer> datas = customerDao.findAllByPager(pb.getIndex(), ps);
		pb.setBeanList(datas);
		
		/*
		 * 7. 保存PageBean到request中
		 */
		request.setAttribute("pb", pb);
		
		/*
		 * 8. 转发到list.jsp
		 */
		return "f:/jsp/cstm/list.jsp";
	}
//	public String findAll(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		/*
//		 * 1. 调用dao获取List<Customer>
//		 * 2. 保存到request中
//		 * 3. 转发到list.jsp显示
//		 */
//		List<Customer> customerList = customerDao.findAll();
//		request.setAttribute("customerList", customerList);
//		return "f:/jsp/cstm/list.jsp";
//	}
	
	/**
	 * 高级搜索
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取pc，如果pc不存在，设置pc=1
		 * 2. 获取url，如果url中包含了&pc=，那么切除它
		 * 3. 获取表单数据
		 * 4. 获取tr
		 * 5. 设置ps
		 * 6. 创建PageBean
		 * 7. 获取datas
		 * 8. 保存PageBean到request中
		 * 9. 转发到list.jsp
		 */
		/*
		 * 1. 获取pc，即当前页码
		 */
		String pageCode = request.getParameter("pc");
		int pc = 1;
		if(pageCode != null && !pageCode.trim().isEmpty()) {
			pc = Integer.parseInt(pageCode);
		}
		
		/*
		 * 2. 获取url，即请求路径及参数
		 */
		String url = request.getRequestURI() + "?" + request.getQueryString();
		int lastIndex = url.lastIndexOf("&pc=");
		if(lastIndex != -1) {
			url = url.substring(0, lastIndex);
		}	
		/*
		 * 3. 获取表单数据
		 */
		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		
		/*
		 * 4. 获取tr，即总记录数
		 */
		int tr = customerDao.countQuery(criteria);
		
		/*
		 * 5. 设置ps，即每页记录数
		 */
		int ps = 10;
		
		/*
		 * 6. 创建PageBean
		 */
		PageBean<Customer> pb = new PageBean<Customer>(pc, tr, ps);
		pb.setUrl(url);
		
		/*
		 * 7. 获取datas，即当前页记录
		 */
		List<Customer> datas = customerDao.queryByPager(criteria, pb.getIndex(), ps);
		pb.setBeanList(datas);
		
		/*
		 * 8. 保存PageBean到request中
		 */
		request.setAttribute("pb", pb);
		
		/*
		 * 9. 转发到list.jsp
		 */
		return "f:/jsp/cstm/list.jsp";
	}
//	public String query(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		/*
//		 * 1. 封装表单参数到Customer对象中
//		 * 2. 调用dao方法得到List<Customer>
//		 * 3. 保存到request中
//		 * 4. 转发到list.jsp显示
//		 */
//		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		List<Customer> customerList = customerDao.query(criteria);
//		request.setAttribute("customerList", customerList);
//		return "f:/jsp/cstm/list.jsp";
//	}
}
