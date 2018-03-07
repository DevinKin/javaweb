package cn.itcast.dao;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private static Properties props;
    static  {
        //加载配置文件内容到props中
        try {
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            props = new Properties();
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 返回一个具体UserDao具体实现类对象
     * 通过配置文件得到dao实现类的名称
     * 通过类名称，完成创建类对象！(反射完成的！)
     * @return
     */
    public static UserDao getUserDao() {
        /**
         * 给出一个配置文件，文件给出UserDao接口的实现类名称
         * 我们这个方法，获取实现类的类名，通过反射完成创建对象
         */
        /**
         * 得到dao实现类的名称
         */
        String daoClassName = props.getProperty("cn.itcast.dao.UserDao");
        try {
            Class clazz = Class.forName(daoClassName);
            return (UserDao) clazz.newInstance();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}
