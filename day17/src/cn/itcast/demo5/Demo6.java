package cn.itcast.demo5;

import cn.itcast.jdbcUtils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo6 {
    @Test
    public void func5() throws SQLException {
        Connection con = JdbcUtils.getConnection();

        String sql = "INSERT INTO t_stu VALUES(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);

        // 疯狂的添加参数
        for (int i = 10000; i < 20000; i++) {
            pstmt.setInt(1, i+1);
            pstmt.setString(2,"stu_" + i);
            pstmt.setInt(3,i);
            pstmt.setString(4,i%2==0?"男":"女");

            //添加批！这一组参数就保存到集合中了。
            pstmt.addBatch();
        }
        long start = System.currentTimeMillis();
        //执行批!
        pstmt.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
