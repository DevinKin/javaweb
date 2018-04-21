package cn.devinkin.javamail;

import cn.itcast.mail.AttachBean;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Demo1 {
    @Test
    public void func1() throws MessagingException {
        /**
         * 1. 得到session
         */
        Properties props = new Properties();
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("devinkin","kingking6666");
            }
        };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //设置发件人
        msg.setFrom(new InternetAddress("devinkin@163.com"));
        //设置收件人
        msg.setRecipients(Message.RecipientType.TO, "499712826@qq.com");
        //设置抄送
        msg.setRecipients(Message.RecipientType.CC, "499712926@souhu.com");
        //设置暗送
        msg.setRecipients(Message.RecipientType.BCC, "499712826@sina.com");

        msg.setSubject("这是来自devinkin的测试邮件");
        msg.setContent("这就是一封垃圾邮件","text/html;charset=utf-8");

        /**
         * 3. 发送邮件
         */
        Transport.send(msg);
    }

    /**
     * 带有附件的邮件！！！
     */
    @Test
    public void func2() throws MessagingException, IOException {
        /**
         * 1. 得到session
         */
        Properties props = new Properties();
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("devinkin","kingking6666");
            }
        };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //设置发件人
        msg.setFrom(new InternetAddress("devinkin@163.com"));
        //设置收件人
        msg.setRecipients(Message.RecipientType.TO, "499712826@qq.com");
        //设置抄送
        msg.setRecipients(Message.RecipientType.CC, "499712926@souhu.com");
        //设置暗送
        msg.setRecipients(Message.RecipientType.BCC, "499712826@sina.com");

        msg.setSubject("这是来自devinkin的测试邮件，有附件");
//        msg.setContent("这就是一封垃圾邮件","text/html;charset=utf-8");

        ///////////////////////////////////////////////////////////////////////////
        /**
         * 当发送包含附件的邮件时，邮件体就为多部件形式！
         * 1. 创建一个多部件的部件内容
         *  MimeMultipart就是一个集合，用来装载多个主体部件！
         * 2. 我们需要创建两个主体部件，一个是文本内容的，另一个是附件的。
         *  主体部件叫MimeBodyPart
         * 3. 把MimeMultipart设置给MimeMessage的内容！
         */
        MimeMultipart msgList = new MimeMultipart();
        //创建MimeBodyPart
        MimeBodyPart part1 = new MimeBodyPart();
        //设置主体部件的内容
        part1.setContent("这是一封包含附件的垃圾邮件", "text/html;charset=utf-8");
        //把主体部件添加到集合中
        msgList.addBodyPart(part1);

        //创建MimeBodyPart
        MimeBodyPart part2 = new MimeBodyPart();
        part2.attachFile(new File("/home/king/test.jpeg"));
        //设置显示的文件名称，其中encodeText用来处理中文乱码问题
        part2.setFileName(MimeUtility.encodeText("垃圾电影.jpeg"));
        //把主体2部件添加到集合中
        msgList.addBodyPart(part2);
        //把它设置给邮件作为邮件的内容
        msg.setContent(msgList);
        ///////////////////////////////////////////////////////////////////////////


        /**
         * 3. 发送邮件
         */
        Transport.send(msg);
    }

    @Test
    public void func3() throws IOException, MessagingException {
        /**
         * 1. 得到session
         */
        Session session = MailUtils.createSession("smtp.163.com",
                "devinkin", "kingking6666");
        /**
         * 2. 创建邮件对象
         */
        Mail mail = new Mail("devinkin@163.com",
                "499712826@qq.com",
                "不是垃圾邮件能是什么", "这里是正文");

        /**
         * 创建两个附件对象
         */
        AttachBean ab1 = new AttachBean(new File("/home/king/test.jpeg"),"垃圾电影.jpeg");
        AttachBean ab2 = new AttachBean(new File("/home/king/test.md"), "blog.md");

        //添加到mail中
        mail.addAttach(ab1);
        mail.addAttach(ab2);

        /**
         * 3. 发送
         */
        MailUtils.send(session, mail);
    }
}


