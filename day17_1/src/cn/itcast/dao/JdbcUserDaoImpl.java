package cn.itcast.dao;

import cn.itcast.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            /**
             * 1.得到连接
             */
            conn = JdbcUtilss.getConnection();

            /**
             * 2.准备sql模版，得到pstmt
             */
            String sql = "SELECT * FROM t_user WHERE username=?";
            pstmt = conn.prepareStatement(sql);

            /**
             * 3.为pstmt中的问号赋值
             */
            pstmt.setString(1, username);
            /**
             * 4.执行sql
             */
            rs = pstmt.executeQuery();

            /**
             * 把rs转换成User类型，返回!
             */
            if (rs == null) return null;

            if (rs.next()) {
                //转换成User对象，并返回
                //ORM --> 对象关系映射！
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                 throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void add(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            /**
             * 1.得到连接
             */
            conn = JdbcUtilss.getConnection();

            /**
             * 2.准备sql模版，得到pstmt
             */
            String sql = "INSERT INTO t_user VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            /**
             * 3.为pstmt中的问号赋值
             */
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getAge());
            pstmt.setString(4, user.getGender());

            /**
             * 4.执行sql
             */
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
