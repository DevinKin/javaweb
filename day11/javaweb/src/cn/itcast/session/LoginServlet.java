package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 校验验证码
         * 1. 从session中获取正确的验证码
         * 2. 从表单中获取用户填写的验证码
         * 3. 进行比较
         * 4. 如果相同,向下执行,否则保存错误信息发送到Request域中
         */
        String verifyCode = (String) request.getSession().getAttribute("VerifyCode");
        String inputCode = request.getParameter("verifyCode");
        if (!inputCode.equals(verifyCode)) {
            String errInfo = "验证码错误";
            request.setAttribute("VerifyCodeError", errInfo);
            request.getRequestDispatcher("/session2/login.jsp").forward(request,response);
            return;
        }

        //处理中文乱码
        request.setCharacterEncoding("utf-8");
        //1.获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        //2.校验用户名是否正确,失败,保存错误信息到request域中,转发回login.jsp
        if ("itcast".equalsIgnoreCase(username)) {
            //在request域中保存错误信息
            String errorInfo = "用户名错误";
            request.setAttribute("userError", errorInfo);
            //将错误信息转发到login.jsp中
            request.getRequestDispatcher("/session2/login.jsp").forward(request, response);
            return;
        }
        else {
            //附加项：把用户名保存到cookies中,发送给客户端浏览器
            //当再次打开Login.jsp,login.jsp会再次读取request的cookies,把他显示到用户名文本框中
            Cookie cookie = new Cookie("user", username);
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            //成功,保存用户信息到session中,重定向到succ1.jsp
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //response.setHeader("Location", "/session2/succ1.jsp");
            //response.setStatus(302);
            response.sendRedirect("/day11/session2/succ1.jsp");
        }

    }
}
