package cn.devinkin.dbUtils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo1 {
    @Test
    public void addStu(Stu stu) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            String sql = "INSERT INTO t_user VALUES(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,stu.getSid());
            pstmt.setString(2,stu.getSname());
            pstmt.setInt(3,stu.getAge());
            pstmt.setString(4,stu.getGender());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateStu(Stu stu) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            String sql = "UPDATE t_user SET sname=?, age=?, gender=? WHERE sid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,stu.getSname());
            pstmt.setInt(2,stu.getAge());
            pstmt.setString(3,stu.getGender());
            pstmt.setInt(4,stu.getSid());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            pstmt.close();
            con.close();

        }
    }

    public void deleteStu(int sid) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcUtils.getConnection();
            String sql = "DELETE FROM t_user WHERE sid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,sid);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            pstmt.close();
            con.close();
        }
    }

    public Stu find(int sid) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcUtils.getConnection();
            String sql = "SELECT * FROM t_stu WHERE sid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,sid);

            rs = pstmt.executeQuery();

            Stu stu = new Stu();
            stu.setSid(rs.getInt("sid"));
            stu.setSname(rs.getString("sname"));
            stu.setAge(rs.getInt("age"));
            stu.setGender(rs.getString("gender"));
            return stu;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            rs.close();
            pstmt.close();
            con.close();
            return null;
        }
    }

    public void findAll() {

    }
}
