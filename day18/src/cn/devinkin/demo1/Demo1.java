package cn.devinkin.demo1;

import cn.devinkin.Dao.AccountDao;
import cn.devinkin.jdbcUtils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo1 {
    /**
     * 转账方法
     * 所有对Connection的操作都在service层进行的处理！Connection应该出现在Dao层中
     * 明天要去处理这一问题，把所有对Connection的操作隐藏起来，需要使用自定义的小工具
     * @param from
     * @param to
     * @param money
     */
    public void zhuanzhang(String from, String to, double money) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            /**
             * 开启事务
             * ...
             * 提交事务
             */
            conn.setAutoCommit(false);

            AccountDao dao = new AccountDao();
            dao.updateBalance(conn,from, -money);        //给from减去相应金额
            if (true) {
                throw  new RuntimeException("不好意思");
            }
            dao.updateBalance(conn, to,money);           //给to加上相应金额
            conn.commit();
            conn.close();
        } catch (Exception e) {
            /**
             * 回滚事务
             */
            try {
                conn.rollback();
            } catch (SQLException el) {
            }
            throw  new RuntimeException(e);
        }
    }

    @Test
    public void func1() {
        zhuanzhang("zs","ls",100);
    }
}
