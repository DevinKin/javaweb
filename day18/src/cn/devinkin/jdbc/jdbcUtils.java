package cn.devinkin.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class jdbcUtils {
    //配置文件的默认配置！要求你必须给出c3p0-config-xml！！！
    private static ComboPooledDataSource dataSource = new  ComboPooledDataSource();
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
