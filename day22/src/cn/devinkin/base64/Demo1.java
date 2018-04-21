package cn.devinkin.base64;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Demo1 {
    @Test
    public void test() throws IOException {
        //BASE64编码
        String s = "kingking6666";
        BASE64Encoder encoder = new BASE64Encoder();
        s = encoder.encode(s.getBytes("UTF-8"));
        System.out.println(s);

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(s);
        System.out.println(new String(bytes,"UTF-8"));
    }
}
