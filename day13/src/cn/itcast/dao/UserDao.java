package cn.itcast.dao;

import cn.itcast.domain.User;

public class UserDao {
    //把xml中的数据查询出来后,封装到User对象里面,然后返回
    public User find() {
        return new User("zhangsan", "123");
    }
}
