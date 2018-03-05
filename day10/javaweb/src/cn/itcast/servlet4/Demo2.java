package cn.itcast.servlet4;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 演示CLassLoader和Class获取路径资源
 */
public class Demo2 {
    @Test
    public void fun1() throws IOException {
        /**
         * ClassLoader获取资源时,不能以"/"开头!
         */
        //得到类加载器
        ClassLoader cl = Demo2.class.getClassLoader();
        //让类加载器去类路径下查找资源
        InputStream in = cl.getResourceAsStream("a.html");
        System.out.println(IOUtils.toString(in));
    }

    /**
     * 使用Class来加载类路径下的资源
     * @throws IOException
     */
    @Test
    public void func2() throws IOException {
        Class c = Demo2.class;
        //与类加载器的效果相同
        InputStream in = c.getResourceAsStream("a.html");
        System.out.println(IOUtils.toString(in));
    }
}
