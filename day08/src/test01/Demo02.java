package test01;


import cn.itcast.vcode.utils.VerifyCode;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo02 {
    @Test
    public void fun1() throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bi = verifyCode.getImage();       //随机生成图片
        System.out.println(verifyCode.getText());       //打印图片上的文本内容

        VerifyCode.output(bi, new FileOutputStream("/home/king/picture/a.jpg"));
    }
}
