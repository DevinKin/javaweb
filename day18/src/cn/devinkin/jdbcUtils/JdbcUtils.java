package cn.devinkin.jdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * v1.0
 * @author king
 */
public class JdbcUtils {
    private static Properties props = null;
    static {
        // 给props进行初始化，即加载dbconfig.properties文件到props对象中
        try{
            InputStream in = JdbcUtils.class
                    .getClassLoader().getResourceAsStream("dbconfig.properties");
            props = new Properties();
            props.load(in);
        } catch (IOException e ) {
            throw new RuntimeException(e);
        }

        try {
            // 加载驱动类
            Class.forName(props.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        /**
         * 1. 加载配置文件
         * 2. 加载驱动类
         * 3. 调用DriverManager.getConnection()
         */
        //得到Connection
        return DriverManager.getConnection(props.getProperty("url"),props.getProperty("username"),props.getProperty("password"));
    }
}
