package cn.devinkin.dbUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QR<T> {
    private DataSource dataSource;

    public QR(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public QR() {
        super();
    }

    /**
     * 做insert、update、delete
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            //通过连接池得到连接对象
            con = dataSource.getConnection();
            //使用sql创建pstmt
            pstmt = con.prepareStatement(sql);
            //设置参数
            initParams(pstmt, params);
            //执行
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initParams(PreparedStatement pstmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i+1,params[i]);
        }
    }

    public T query(String sql, RsHandler<T> rh, Object... params) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //通过连接池得到连接对象
            con = dataSource.getConnection();
            //使用sql创建pstmt
            pstmt = con.prepareStatement(sql);
            //设置参数
            initParams(pstmt, params);
            //执行
            rs = pstmt.executeQuery();
            return rh.handle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


interface RsHandler<T> {
    public T handle(ResultSet rs) throws SQLException;
}