package cn.devinkin.bookstore.user.dao;

import cn.devinkin.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * User持久层
 */
public class UserDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 按用户名查询
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM tb_user WHERE username=?";
            return qr.query(sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按邮箱名查询
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        try {
            String sql = "SELECT * FROM tb_user WHERE email=?";
            return qr.query(sql, new BeanHandler<User>(User.class), email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加用户到数据库
     * @param user
     */
    public void add(User user) {
        try {
            String sql = "INSERT INTO tb_user VALUES(?,?,?,?,?,?);";
            Object[] params = {user.getUid(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getCode(), user.isState()};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按激活码查询
     * @param code
     * @return
     */
    public User findByCode(String code) {
        try {
            String sql = "SELECT * FROM tb_user WHERE code=?";
            return qr.query(sql, new BeanHandler<User>(User.class), code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改指定用户的指定状态
     * @param uid
     * @param state
     */
    public void updateState(String uid, boolean state) {
        try {
            String sql = "UPDATE tb_user SET state=? WHERE uid=?";
            Object[] params = {state, uid};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
