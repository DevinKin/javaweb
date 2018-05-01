package cn.devinkin.bookstore.user.service;

import cn.devinkin.bookstore.user.dao.UserDao;
import cn.devinkin.bookstore.user.domain.User;

/**
 * User业务层
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 注册功能
     * @param form
     */
    public void regist(User form) throws UserException {
        //校验用户名
        User user = userDao.findByUsername(form.getUsername());
        if (user != null) {
            throw new UserException("用户名已经被注册");
        }

        //校验邮箱名
        user = userDao.findByEmail(form.getEmail());
        if (user != null) {
            throw new UserException("邮箱名已经被注册");
        }

        //插入用户到数据库中
        userDao.add(form);
    }

    /**
     * 激活
     * @param code
     * @throws UserException
     */
    public void active(String code) throws UserException {
        /**
         * 1. 使用code查询数据库，得到user
         */
        User user = userDao.findByCode(code);
        /**
         * 2. 如果user不存在，激活码错误
         */
        if (user == null) throw new UserException("激活码无效！");
        /**
         * 3. 校验用户的状态是否未激活，如果已激活，说明是二次激活，抛出异常
         */
        if (user.isState()) throw new UserException("您已经激活过了，不要再激活了！");

        /**
         * 4. 修改用户的状态
         */
        userDao.updateState(user.getUid(), true);
    }

    /**
     * 登录
     * @param form
     * @return
     * @throws UserException
     */
    public User login(User form) throws UserException {
        /**
         * 1. 使用username查询数据库，得到user对象
         *  1. 如果user为null，抛出异常(用户不存在)
         */
        User user = userDao.findByUsername(form.getUsername());
        if (user == null) throw new UserException("用户不存在!");
        /**
         * 2. 比较form和user的密码是否相同
         *  1.若不同，抛出异常(密码错误)
         */
        if (!user.getPassword().equals(form.getPassword())) throw new UserException("密码错误!");

        /**
         * 3. 查看用户状态
         *  1. 如果未激活，抛出异常(你还没有激活)
         */
        if (!user.isState()) throw new UserException("你尚未激活!");

        /**
         * 4. 返回user
         */
        return user;
    }
}
