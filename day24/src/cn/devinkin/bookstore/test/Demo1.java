package cn.devinkin.bookstore.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;

public class Demo1 {
    @Test
    public void func1() {
        /**
         * 包含了占位符的字符串就是末班
         * 占位符：{0}、{1}、{2}
         * 可变参数，需要指定模板中占位符的值，有几个占位符就提供几个参数
         */
        MessageFormat.format("","","","");
    }

    @Test
    public void func2() {
        System.out.println(2.0 - 1.1);
    }

    @Test
    public void func3() {
        BigInteger n = BigInteger.valueOf(20);
        BigInteger sum = BigInteger.valueOf(1);
        for (int i = 1; i <= 100; i++) {
            BigInteger bi = BigInteger.valueOf(i);
            sum = sum.multiply(bi);
        }
        System.out.println(sum);

    }

    /**
     * BigDecimal
     * 1. 可以处理二进制运算的误差
     */
    @Test
    public void func4() {
        /**
         * 1. 创建BigDecimal对象时，必须使用String构造器！
         */
        BigDecimal d1 = new BigDecimal(2.0);
        BigDecimal d2 = new BigDecimal("1.1");
        BigDecimal d3 = d1.subtract(d2);
        System.out.println(d3);
    }

    /**
     * 浏览器不支持
     * https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=123456&p3_Amt=1&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookstore/OrderServlet?method=back&p9_SAF=&pa_MP=&pd_FrpId=ICBC-NET-B2C&pr_NeedResponse=1&hmac=76befbe4ab0f4aa7b021d6628efc2d81
     * 建行成功
     * https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=123456&p3_Amt=1&p4_Cur=CNY&p5_Pid=&p6_Pcat=&p7_Pdesc=&p8_Url=http://localhost:8080/bookstore/OrderServlet?method=back&p9_SAF=&pa_MP=&pd_FrpId=CCB-NET-B2C&pr_NeedResponse=1&hmac=9fc0bca72f423233bb345302663b1d40
     *
     */

    @Test
    public void fun5() {
        String hmac = PaymentUtil.buildHmac("Buy", "10001126856", "123456", "1", "CNY",
                "", "", "", "http://localhost:8080/bookstore/OrderServlet?method=back",
                "", "", "CCB-NET-B2C", "1", "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
        System.out.println(hmac);
    }

    @Test
    public void func6() {
        Date date = new Date();
        System.out.println(date);
    }
}
