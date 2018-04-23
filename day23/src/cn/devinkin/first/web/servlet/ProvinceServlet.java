package cn.devinkin.first.web.servlet;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /**
         * 响应所有省份名称，使用逗号分隔！
         */

        /**
         * 1. 得到Document对象
         *  1. 创建解析器对象
         *  2. 调用解析器的读方法，传递一个流对象，得到Document
         */
        SAXReader reader = new SAXReader();
        InputStream input = this.getClass().getResourceAsStream("/china.xml");
        try {
            Document doc = reader.read(input);

            /**
             * 2. 查询所有province的name属性
             *  循环遍历，把所有的属性值连接成一个字符串，发送给客户端
             */
            List<Attribute> arrList = doc.selectNodes("//province/@name");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrList.size(); i++) {
                //把每个属性的值存放在StringBuilder
                sb.append(arrList.get(i).getValue());
                if (i < arrList.size() - 1) {
                    sb.append(",");
                }
            }
            response.getWriter().print(sb);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }
}
