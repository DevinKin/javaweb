package cn.itcast.demo3;

import org.junit.Test;

import java.sql.*;

/**
 * PreapredStatement
 * 防SQL攻击
 */
public class Demo3 {
    /**
     * 登录
     * 使用username和password去查询数据库
     * 若查出结果集,说明正确！返回true
     * 若查不出结果,说明用户名或密码错误
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) throws SQLException {
        String DriverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String mysqlUsername = "root";
        String mysqlPassword = "123";
        Connection conn = null;
        Statement sttm = null;
        ResultSet res = null;

        try {
            Class.forName(DriverClassName);

            conn = DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
            sttm = conn.createStatement();
            String sql = "SELECT * FROM t_user WHERE username='" + username + "' AND password='" + password + "'";
            res = sttm.executeQuery(sql);
            return res.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            res.close();
            sttm.close();
            conn.close();
        }
    }

    @Test
    public void fun1() throws Exception {
        String username = "a' or  'a' = 'a";
        String password = "a' or  'a' = 'a";
        boolean bool = login(username, password);
        System.out.println(bool);
    }
}
