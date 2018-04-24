package cn.devinkin.pcconnect.web.servlet;

import cn.devinkin.pcconnect.domain.Province;
import cn.devinkin.pcconnect.service.Service;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProServlet")
public class ProServlet extends HttpServlet {
    private Service service = new Service();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /**
         * 1. 通过DAO得到所有的省份
         * 2. 把List<Province>转换为json
         * 3. 将json响应到页面中
         */
        List<Province> proList = service.findAllProvince();
        String proJson = JSONArray.fromObject(proList).toString();
        response.getWriter().print(proJson);
    }
}
