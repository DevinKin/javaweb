package cn.itcast.dao;

import cn.itcast.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.Enumeration;
import java.util.List;

/**
 * 数据类
 * 某一实现类
 */
public class UserDaoImpl implements UserDao {
    //依赖数据文件
    private String path = "e:\\javaweb\\users.xml";

    /**
     * 按用户名查询
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        /**
         * 1.得到Document
         * 2.xpath查询
         * 3.校验查询结果是否为null,如果为null,返回null
         * 4.如果不为null,需要把Element封装到User对象中
         */

        //1.得到Document
        //创建解析器
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path);
            //2.xpath查询得到element
            Element element = (Element)document.selectSingleNode("//user[@username='" + username + "']");

            //3.校验查询结果是否为null,如果为null,返回null
            if (element == null) return null;

            //4.如果不为null,需要把Element封装到User对象中
            User user = new User();
            String usn = element.attributeValue("username");
            String pwd = element.attributeValue("password");
            user.setUsername(usn);
            user.setPassword(pwd);
            return user;

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void add(User user) {
        /**
         * 1.得到Document
         * 2.通过Document得到root元素！<users></users>
         * 3.使用参数user,转发成element对象
         * 4.把element添加到root元素中
         * 5.保存Document
         */

        try {
            //1.得到Document
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(path);

            //2.通过Document得到root元素
            Element root = document.getRootElement();

            //3.使用参数user，转变成element对象中
            Element nUser = root.addElement("user");
            nUser.addAttribute("username", user.getUsername());
            nUser.addAttribute("password",user.getPassword());
            nUser.addAttribute("age", new String(String.valueOf(user.getAge())));
            nUser.addAttribute("gender",user.getGender());

            try {
                //保存xml
                XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),
                        "UTF-8"),
                        OutputFormat.createPrettyPrint());
                xmlWriter.write(document);
                xmlWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
