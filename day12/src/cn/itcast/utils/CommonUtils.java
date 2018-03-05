package cn.itcast.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.UUID;

public class CommonUtils {
    /**
     * 生成不重复的32位长的大写字符串
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 把Map转换成指定类型的JavaBean对象
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
            /**
             * 1.创建指定类型的javabean对象
             */
            T bean = clazz.newInstance();
            /**
             * 2.把数据封装到javabean中
             */
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
