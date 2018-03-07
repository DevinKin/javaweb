package cn.itcast.dao;

import cn.itcast.domain.User;

import java.sql.SQLException;

/**
 * UserDao接口
 * @author king
 */
public interface UserDao {
    public User findByUsername(String username);
    public void add(User user) throws SQLException;
}
