package cn.devinkin.dbutils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class jdbcUtils {
    //配置文件的默认配置！要求你必须给出c3p0-config-xml!!!
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //它是事务专用连接
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static Connection getConnection() throws SQLException {
        //当con不等于null，说明已经调用过beginTransaction()，表示开启过事务
        Connection con = tl.get();
        if (con != null) return con;
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象！
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 开启事务
     * 1. 获取一个Connection，设置它的setAutoCommit(false)
     * 2. 还要保证dao中使用的链接是我们刚刚创建的
     * ----------------------------------------
     * 1. 创建一个Connection，设置为手动提交
     * 2. 把这个Connection给dao用
     * 3. 还要让commitTransaction或rollbackTransaction可以获取到
     */
    public static void beginTransaction() throws SQLException {
        Connection con = tl.get();
        if (con != null)
            throw new SQLException("已经开启了事务，就不要重复开启事务了");
        /**
         * 1. 给con赋值！
         * 2. 给con设置为手动提交
         */
        con = getConnection();
        con.setAutoCommit(false);

        //把当前线程的连接保存起来
        tl.set(con);
    }

    /**
     * 提交事务
     * 1. 获取beginTransaction提供的Connection，然后调用commit方法
     */
    public static void commitTransaction() throws SQLException {
        //获取当前线程的专用连接
        Connection con = tl.get();
        if (con == null)
            throw new SQLException("还没有开启事务，不能提交");
        /**
         * 1、直接使用con.commit()
         */
        con.commit();
        con.close();
        //把它设置为null，表示事务已经结束了！下次调用getConnection()返回就不是con了
        tl.remove();    //从tl中移除连接
    }

    /**
     * 回滚事务
     * 1. Huoqu beginTransaction提供的Connection，然后调用rollback方法
     */
    public static void rollbackTransaction() throws SQLException {
        Connection con = tl.get();
        if (con == null)
            throw new SQLException("还没有开启事务，不能提交");
        /**
         * 1. 直接使用con.rollback()
         */
        con.rollback();
        con.close();
        //把它设置为null，表示事务已经结束了！下次调用getConnection()返回就不是con了
        tl.remove();   //从tl中移除连接
    }

    /**
     * 释放连接
     * @param connection
     */
    public static void release(Connection connection) throws SQLException {
        /**
         * 判断它是不是事务专用，如果是事务专用，就不关闭
         * 如果不是事务专用，那么就要关闭!
         */
        //如果con == null，说明现在没有事务，那么connection一定不是事务专用的
        Connection con = tl.get();
        if (con == null)connection.close();
        //如果con != null，说明有事务，那么需要判断参数连接是否与con相等，若不等，说明参数不是事务专用连接
        if (con != connection)  connection.close();
    }
}
