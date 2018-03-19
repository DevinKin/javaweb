package cn.devinkin.dbutils;

import org.junit.Test;
import java.sql.SQLException;

public class Demo1 {
    private AccountDao dao = new AccountDao();
    @Test
    public void serviceMethod() throws Exception {
        try {
            jdbcUtils.beginTransaction();
            dao.update("zs",-200);

            dao.update("ls",100);
            jdbcUtils.commitTransaction();
        } catch (Exception e) {
            try {
                jdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        }
    }
}


