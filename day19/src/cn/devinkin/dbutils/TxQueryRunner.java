package cn.devinkin.dbutils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这个类中的方法，自己处理连接的问题
 * 无需外界传递！
 * 怎么处理的呢？
 *  通过jdbcUtils.getConnection()得到连接！有可能是事务连接，也可能是普通连接！
 *  jdbcUtils.release（）完成对连接的释放！如果是普通连接类，则关闭之！
 */
public class TxQueryRunner extends QueryRunner{
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        /**
         * 1. 得到连接
         * 2. 执行父类方法，连接对象
         * 3. 释放链接
         * 4. 返回值
         */
        Connection con = jdbcUtils.getConnection();
        int[] result = super.batch(sql,params);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        T result =  super.query(con,sql, param, rsh);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        T result =  super.query(con,sql, params, rsh);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        T result = super.query(con,sql, rsh, params);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        T result = super.query(con,sql, rsh);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        int result =  super.update(con,sql);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        int result = super.update(con, sql, param);
        jdbcUtils.release(con);
        return result;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection con = jdbcUtils.getConnection();
        int result = super.update(con, sql, params);
        jdbcUtils.release(con);
        return result;
    }
}
