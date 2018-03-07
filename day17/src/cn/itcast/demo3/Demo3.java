package cn.itcast.demo3;

import cn.itcast.jdbcUtils.JdbcUtils;
import org.junit.Test;

import java.io.IOException;
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
        String url = "jdbc:mysql://localhost:3306/mydb3?userServerPrepStmts=true&cachePrepStmts=true";
        String mysqlUsername = "root";
        String mysqlPassword = "root123";
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


    public boolean login2(String username, String password) throws SQLException {
        String DriverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String mysqlUsername = "root";
        String mysqlPassword = "root123";
        Connection conn = null;
        /**
         * 得到PreparedStatement
         * 1.给出sql模版：所有的参数使用?来替代
         * 2.调用connection方法得到PreparedStatement
         */
        String sql = "SELECT * FROM t_user WHERE username=? AND password=? ";
        PreparedStatement preparedStatement = null;
        ResultSet res = null;

        try {
            Class.forName(DriverClassName);

            conn = DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
            preparedStatement = conn.prepareStatement(sql);
            /**
             * 二、为参数赋值
             */
            //给第一个问号赋值，值为username
            preparedStatement.setString(1,username);
            //给第二个问号赋值，值为password
            preparedStatement.setString(2,password);
            res = preparedStatement.executeQuery();
            return res.next();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            res.close();
            preparedStatement.close();
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

    @Test
    public void fun2() throws Exception {
        //String username = "a' or  'a' = 'a";
        //String password = "a' or  'a' = 'a";
        String username = "zhangsan";
        String password = "123";
        boolean bool = login2(username, password);
        System.out.println(bool);
    }

    @Test
    public void func3() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
}
