package cn.itcast.demo1;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo1 {
    /**
     * ClassNotFoundException：
     * 没有导入驱动包
     * 类不存在
     *
     * SQLException:
     * 检查三个参数:url,username,password是否正确
     * 检查是否开启了mysql服务器
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void func1() throws ClassNotFoundException, SQLException {
        /**
         * jdbc四大配置参数：
         * driverClassName: com.mysql.jdbc.Driver
         * url: jdbc:mysql://localhost:3306/mydb3
         * username:
         * password:
         */

        /**
         * Class.forName()会加载类的静态代码块
         */
        Class.forName("com.mysql.jdbc.Driver");
        //com.mysql.jdbc.Driver driver = new Driver();
        //DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String username = "root";
        String password = "123";

        //使用url,username,password,得到连接对象
        Connection con = DriverManager.getConnection(url,username,password);
        System.out.println(con);
    }
}
