package cn.devinkin.onlinehd.test;

import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Demo {
    @Test
    public void test1() {
        System.out.println(LocalDateTime.now());
        String hcode = "AB";
        File file = new File("test", hcode.charAt(0) + "/" + hcode.charAt(1));
        System.out.println(file.getPath());
    }
}
