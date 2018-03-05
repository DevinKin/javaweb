package cn.itcast.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.domain.User;
import cn.itcast.service.UserException;
import cn.itcast.service.UserService;
import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //依赖UserService
        UserService userService = new UserService();

        /**
         * 1.封装表单数据(封装User对象中)
         */
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);

        /**
         * 添加新任务(表单校验)
         * 1.创建一个Map,用来装载所有表单错误信息
         *  在校验过程中,如果失败,向map添加错误信息,其中key为表单字段名称
         * 2.校验之后,查看map长度是否大于0,如果大于0,说明有错误信息!
         *  保存map到request,保存form到request,转发到regist.jsp
         *
         * 3.如果map为空,说明没有错误信息,向下执行
         */
        //用来装载所有错误信息
        Map<String,String> errors = new HashMap<String,String>();
        //对用户名进行校验
        String username = form.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空!");
        } else if (username.length() < 3 || username.length() > 15) {
            errors.put("username", "用户名长度在3~15以内");
        }

        //对密码进行校验
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空!");
        } else if (password.length() < 3 || password.length() > 15) {
            errors.put("password", "密码长度在3~15以内");
        }

        //对验证码进行校验
        String sessionVerifyCode = (String)request.getSession().getAttribute("session_vc");
        String verifyCode = form.getVerifyCode();
        if (verifyCode == null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode", "验证码不能为空!");
        } else if (verifyCode.length() != 4) {
            errors.put("verifyCode", "验证码长度为4位");
        } else if(!verifyCode.equals(sessionVerifyCode)) {
            errors.put("verifyCode", "验证码错误!");
        }


        /**
         * 判断map是否为空,不为空,说明存在错误
         */
        if(errors != null && errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
            return;
        }



        /**
         * 2.调用userService的regist()方法，传递form过去
         * 3.得到异常,获取异常信息，保存到request域,转发到regist.jsp中去显示
         */
        try {
            userService.regist(form);
            response.getWriter().print("<h1>注册成功!</h1><a href='" +
                    request.getContextPath() +
                    "/user/login.jsp" + "'/>点击这里");

        } catch (UserException e) {
            //获取异常信息
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user", form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
        }

    }
}
