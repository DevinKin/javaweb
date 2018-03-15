package cn.devinkin.Dao;

import cn.devinkin.jdbcUtils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {
    /**
     * 修改指定用户的余额
     * @param name
     * @param balancee
     */
    public void updateBalance(Connection con, String name, double balancee) {
        try {
            /**
             * 2. 给出sql模版，创建pstmt
             */
            String sql = "UPDATE account SET balance=balance+? WHERE name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            /**
             * 3. 对象参数进行赋值
             */
            pstmt.setDouble(1, balancee);
            pstmt.setString(2,name);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
