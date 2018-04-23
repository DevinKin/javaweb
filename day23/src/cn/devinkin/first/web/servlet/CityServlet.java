package cn.devinkin.first.web.servlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "CityServlet")
public class CityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        /**
         * 获取省份名称，加载该省对应的<province>元素
         * 把元素转换为字符串发送给客户端
         */
        /**
         * 1. 获取省份名称
         * 2. 使用省份名称查找到对应的<province>元素
         * 3. 把<province>元素转换成字符串，发送!
         */
        //得到Document
        SAXReader reader = new SAXReader();
        InputStream input = this.getClass().getResourceAsStream("/china.xml");
        try {
            Document doc = reader.read(input);

            /**
             * 获取参数
             */
            //获取省份名称
            String pname = request.getParameter("pname");

            //province[@name='北京']
            Element proELe = (Element) doc.selectSingleNode("//province[@name='" + pname + "']");
            //把元素转换为字符串
            String xmlStr = proELe.asXML();
            response.getWriter().print(xmlStr);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
