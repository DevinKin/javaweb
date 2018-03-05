package cn.itcast.test.dao;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * UserDao的测试类
 */
public class UserDaoTest {
    @Test
    public void testFindByUsername() throws DocumentException, SAXException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername("张飞");
        System.out.println(user);
    }

    @Test
    public void testAdd() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("赵六");
        user.setPassword("zhaoliu");
        userDao.add(user);
    }
}
