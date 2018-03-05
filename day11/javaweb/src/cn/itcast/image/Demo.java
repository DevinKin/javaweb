package cn.itcast.image;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 动态生成图片
 * @author devinkin
 */
public class Demo {
    @Test
    public void fun1() throws IOException {
        /**
         * 1. 创建图片缓冲区
         * 2. 设置其宽高
         * 3. 得到这个图片的绘制环境,得到画笔
         * 4. 保存起来
         */
        BufferedImage bi = new BufferedImage(70,35,BufferedImage.TYPE_INT_RGB);
        //得到绘制环境
        Graphics2D g = (Graphics2D) bi.getGraphics();
        //把环境设置为红色
        g.setColor(Color.RED);
        //填充矩形
        g.fillRect(0,0,70,35);
        g.setColor(Color.WHITE);
        //向图片写入字符串
        g.drawString("Hello",2,35-2);

        ImageIO.write(bi,"JPEG", new FileOutputStream("/home/king/picture/xx.jpg"));

    }

    @Test
    public void fun2() throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage bi = vc.getImage();
        VerifyCode.output(bi, new FileOutputStream("/home/king/picture/xxxx.jpg"));
        System.out.println(vc.getText());
    }
}
