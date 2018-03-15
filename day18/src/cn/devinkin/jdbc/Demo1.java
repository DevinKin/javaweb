package cn.devinkin.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBCP连接池
 */
public class Demo1 {
    @Test
    public void func1() throws SQLException {
        /**
         * 1.创建连接池对象
         * 2.配置四大参数
         * 3.配置池参数
         * 4.得到连接对象
         */

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb3");
        dataSource.setUsername("root");
        dataSource.setPassword("root123");

        dataSource.setMaxActive(20);
        dataSource.setMinIdle(3);
        dataSource.setMaxWait(1000);

        Connection conn = dataSource.getConnection();
        System.out.println(conn.getClass().getName());

        /**
         * 连接池内部使用了四大参数创建了连接对象！即mysql提供的Connection
         * 连接池使用myslq的连接对象进行了装饰，只对close()方法进行了增强
         * 装饰之后的Connection的close()方法，用来把当前链接归还给连接池
         */
        //把连接归还给连接池
        conn.close();
    }
}
