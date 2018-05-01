package cn.devinkin.bookstore.user.dao;

import cn.devinkin.bookstore.user.domain.Admin;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {
    private QueryRunner qr = new TxQueryRunner();

    public Admin findByUsername(String adminname) {
        String sql = "SELECT * FROM tb_admin WHERE username=?";
        try {
            return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
