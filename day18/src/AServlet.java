import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 获取JNDI的资源
 */

@WebServlet(name = "AServlet")
public class AServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 创建JNDI的上下文对象
         */
        try {
            Context cxt = new InitialContext();
            //2. 查询入口
            //Context envContext = (Context)cxt.lookup("java:comp/env");
            //3. 再进行二次查找，找到我们的资源
            //使用的名称与<Resource>元素的名称对应
            //DataSource dataSource = (DataSource) envContext.lookup("jdbc/dataSource");
            DataSource dataSource = (DataSource) cxt.lookup("java:/com/env/jdbc/dataSource");

            Connection con = dataSource.getConnection();
            System.out.println(con);
            con.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
