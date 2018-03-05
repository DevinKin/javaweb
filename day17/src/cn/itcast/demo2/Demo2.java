package cn.itcast.demo2;

import org.junit.Test;

import java.sql.*;

public class Demo2 {
    /**
     * 1. 连接数据库,得到Connection即成功
     * 2.通过Connection对象创建Statement
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void fun1() throws ClassNotFoundException, SQLException {
        /**
         * 1.准备四大参数
         * 2.加载驱动类
         * 3.得到Connection
         */
       String driverClassName = "com.mysql.jdbc.Driver";
       // jdbc协议的格式!
       String url = "jdbc:mysql://localhost:3306/mydb3";
       String username = "root";
       String password = "123";

       Class.forName(driverClassName);
       Connection connection = DriverManager.getConnection(url, username, password);

        /**
         * 对数据库做增,删,改
         * Statement语句的发送器,它的功能就是向数据库发送sql语句
         * 2.调用它的int executeUpdate(String sql),它可以发送DML,DDL
         */

        //1.通过Connection得到Statement对象
        Statement stmt = connection.createStatement();
        //String sql = "INSERT INTO stu VALUES('ITCAST_0003','wangWu',88,'male')";
        //String sql = "UPDATE stu SET name='zhaoliu', age = '22',gender='female' WHERE number='ITCAST_0003'";
        String sql = "DELETE FROM stu";
        int r = stmt.executeUpdate(sql);
        System.out.println(r);
    }

    /**
     * 执行查询
     */
    @Test
    public void func2() throws ClassNotFoundException, SQLException {
        /**
         * 1.得到Connection
         * 2.得到Statement,发送Select语句
         * 3.对查询的表格进行解析！
         */
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/exam";
        String username = "root";
        String password = "123";

        Class.forName(driverClassName);

        Connection connection = DriverManager.getConnection(url,username,password);

        Statement statement = connection.createStatement();

        /**
         * 调用Statement的 ResultSet executeQuery(String querySql)
         */
        ResultSet rs = statement.executeQuery("SELECT * FROM emp");

        /**
         * 解析ResultSet
         * 1.把行光标移动到第一行,可以调用next()方法
         */
        while (rs.next())  { //把光标向下移动一行,并判断下一行是否存在
            //通过列编号获取列值
            int empno = rs.getInt(1);
            //通过列名获取列值
            String ename = rs.getString("ename");
            double sal = rs.getDouble("sal");

            System.out.println(empno + ", " + ename + ", " + sal);
        }

        /**
         * 关闭资源
         */
        rs.close();
        statement.close();
        connection.close();         // 必须要关
    }

    //规范化
    @Test
    public void func3() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            String DriverClassName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/exam";
            String username = "root";
            String password = "123";
            Class.forName(DriverClassName);

            connection = DriverManager.getConnection(url,username,password) ;

            statement = connection.createStatement();

            String sql = "SELECT * FROM emp";
            resultSet = statement.executeQuery(sql);

            /**
             * getString()和getObject()是通用的
             */
            //while (resultSet.next()) {
            //    System.out.println(resultSet.getObject(1) + ", "
            //            + resultSet.getString("ename") + ", "
            //            + resultSet.getDouble("sal"));
            //}
            int count = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    System.out.print(resultSet.getString(i));
                    if (i < count)
                        System.out.print(", ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }
}
