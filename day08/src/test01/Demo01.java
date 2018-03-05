package test01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        /**
         * Image, ImageIO, BufferedImage, Icon, ImageIcon
         */
        //得到图片缓冲区
        BufferedImage b1 = new BufferedImage(150,70,BufferedImage.TYPE_INT_RGB);

        //得到它的绘制环境(拿到图片的笔)
        Graphics2D g2 = (Graphics2D) b1.getGraphics();

        //设置颜色,设置背景白色
        g2.setColor(Color.WHITE);

        //填充整张图片
        g2.fillRect(0,0,150,70);
        //设置字体
        g2.setFont(new Font("宋体",Font.BOLD, 25));
        //设置颜色
        g2.setColor(Color.RED);
        g2.drawRect(0,0,150-3,70-3);
        //向图片上写字符串
        g2.setColor(Color.BLACK);
        g2.drawString("HelloWorld", 3,50);

        ImageIO.write(b1,"PNG",new FileOutputStream("/home/king/picture/day8.png"));
        //保存路径
    }
}
