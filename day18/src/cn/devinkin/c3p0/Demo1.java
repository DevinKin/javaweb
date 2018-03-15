package cn.devinkin.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo1 {
    @Test
    public void func1() throws PropertyVetoException, SQLException {
        /**
         * 在创建连接池对象时，这个对象就会自动加载配置文件！不用我们来指定
         */
        //创建连接池对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        //对池四大参数配置
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb3");
        dataSource.setUser("root");
        dataSource.setPassword("root123");

        //池配置
        dataSource.setAcquireIncrement(5);
        dataSource.setInitialPoolSize(20);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(50);

        Connection con = dataSource.getConnection();
        System.out.println(con);
        con.close();
    }

    /**
     * 使用命名配置
     */
    @Test
    public void func2() throws SQLException {
        /**
         * 构造器的参数指定了命名配置元素的名称！
         */
        ComboPooledDataSource dataSource = new ComboPooledDataSource("oracle-config");


        Connection con = dataSource.getConnection();
        System.out.println(con);
        con.close();
    }
}
