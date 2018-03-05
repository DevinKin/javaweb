package cn.itcast.session;

import cn.itcast.image.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.生成图片
         * 2.保存图片上的文本到session
         * 3.把图片响应给客户端
         */
        //1.生成图片
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();

        //2.保存图片上的文本到session
        String text = vc.getText();
        HttpSession session = request.getSession();
        session.setAttribute("VerifyCode", text);
        //3.把图片响应给客户端
        VerifyCode.output(image, response.getOutputStream());
    }
}
