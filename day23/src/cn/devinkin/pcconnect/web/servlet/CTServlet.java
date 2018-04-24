package cn.devinkin.pcconnect.web.servlet;

import cn.devinkin.pcconnect.domain.City;
import cn.devinkin.pcconnect.service.Service;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CTServlet")
public class CTServlet extends HttpServlet {
    private Service service = new Service();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取名为pid的参数
         * 2. 使用这个省id查询数据库，得到list<City>
         * 3. 转发成son，转发给客户端
         */
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int pid = Integer.parseInt(request.getParameter("pid"));
        List<City> cityList = service.findByProvince(pid);
        String cityJson = JSONArray.fromObject(cityList).toString();
        response.getWriter().print(cityJson);

    }

}
