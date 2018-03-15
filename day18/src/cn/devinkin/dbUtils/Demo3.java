package cn.devinkin.dbUtils;


import cn.itcast.jdbc.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Demo3 {
    @Test
    public void fun1() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "INSERT INTO t_stu VALUES(?,?,?,?)";
        Object[] params = {4,"kaka",88,"女性"};
        qr.update(sql,params);
    }

    @Test
    public void func2() throws SQLException {
        //创建QueryRunner,需要提供数据库连接池对象
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        //给出sql模版
        String sql = "SELECT * FROM t_stu WHERE sid=?";
        //给出参数
        Object[] params = {1};
        /*
        ResultSetHandler<Stu> rsh = new ResultSetHandler<Stu>() {
            @Override
            public Object handle(ResultSet rs) throws SQLException {
                if (!rs.next())
                    return null;
                Stu stu = new Stu();
                stu.setSid(rs.getInt("sid"));
                stu.setSname(rs.getString("sname"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                return (Object)stu;
            }
        };
        */
        //执行query方法，给出结果集处理器ResultSetHandler的实现类对象
        //我们给的是BeanHandler，他实现了ResultSetHandler
        //它需要一个类型，然后它会把rs的数据封装到指定类型的JavaBean对象中，然后返回JavaBean对象
        Stu stu = qr.query(sql, new BeanHandler<Stu>(Stu.class), params);
        System.out.println(stu);
    }

    /**
     * BeanListHandler应用，它是多行处理器
     * @throws SQLException
     */
    @Test
    public void func3() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT * FROM t_stu";
        List<Stu> stuList = qr.query(sql, new BeanListHandler<Stu>(Stu.class));
        System.out.println(stuList);
    }

    /**
     * MapHandler的应用，它是单行处理器把一行转换成一个Map对象
     */
    @Test
    public void func4() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT * FROM t_stu WHERE sid=?";
        Object[] params = {1};
        Map mapStu = qr.query(sql, new MapHandler(),params);
        System.out.println(mapStu);
    }

    /**
     * MapListHandler的应用，它是多行处理器，把每一行都转换为一个Map对象
     */
    @Test
    public void func5() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT * FROM t_stu";
        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
        System.out.println(mapList);
    }

    /**
     * ScalarHandler，它是单行单列时使用，最为适合
     */
    @Test
    public void func6() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM t_stu";
        Object obj = qr.query(sql, new ScalarHandler());
        System.out.println(obj);
        System.out.println(obj.getClass().getName());
    }
}
