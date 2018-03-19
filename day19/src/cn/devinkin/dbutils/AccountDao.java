package cn.devinkin.dbutils;

import org.apache.commons.dbutils.QueryRunner;
import java.sql.SQLException;

public class AccountDao {
    public static void update(String name, double money) throws SQLException {
        QueryRunner qr  = new TxQueryRunner();
        String sql = "UPDATE account SET balance=balance+? WHERE name=?";
        Object[] params = {money,name};
        //我们需要自己来提供连接，保证多次调用使用的是同一个连接
        qr.update(sql, params);
    }

}
