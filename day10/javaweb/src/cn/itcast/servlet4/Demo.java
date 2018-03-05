package cn.itcast.servlet4;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

public class Demo {
    /**
     * [-27, -68, -96, -28, -72, -119]
     * [%E5, %BC, %A0, (256-28)16, ..]
     * %E5%BC%A0%E4%B8%89
     * @throws UnsupportedEncodingException
     */
    @Test
    public void fun1() throws UnsupportedEncodingException {
        String name = "张三";
        byte[] bytes = name.getBytes("utf-8");
        System.out.println(Arrays.toString(bytes));

        String s = URLEncoder.encode(name, "UTF-8");
        System.out.println(s);

        s = URLDecoder.decode(s, "UTF-8");
        System.out.println(s);
    }
}
