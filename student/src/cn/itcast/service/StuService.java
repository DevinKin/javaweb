package cn.itcast.service;

import cn.itcast.utils.Dom4jUtils;
import cn.itcast.vo.Student;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class StuService {

    //增加
    public static void addStu(Student student) throws Exception
    {
        /**
         * 1. 创建解析器
         * 2. 得到document
         * 3. 获取根节点
         * 4. 根节点后添加<stu></stu>标签
         * 5. <stu></stu>标签上面依次添加id name age
         * 6. 在id name age上面依次添加值
         * 7. 回写xml
         */

        //创建解析器
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        // 获取根节点
        Element root = document.getRootElement();

        //根节点后添加<stu></stu>标签
        Element stu = root.addElement("stu");

        //在<stu></stu>标签上面依次添加id name age
        Element id1 = stu.addElement("id");
        Element name1 = stu.addElement("name");
        Element age1 = stu.addElement("age");

        //在id name age上面一次添加值
        id1.setText(student.getId());
        name1.setText(student.getName());
        age1.setText(student.getAge());

        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    //删除 根据学生的id删除
    public static void delStu(String id) throws Exception {
        /**
         * 1.创建解析器
         * 2.得到document
         * 3.获取根节点
         * 4.获取所有的<id></id>标签,使用xpath,返回list集合
         * 5.判读集合里面的id和传递的id是否相同
         * 6.如果相同,把id所在的stu删除,获取对应的stu及其父节点
         * 7.回写xml
         */
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);


        List<Node> list = document.selectNodes("//id");

        for (Node node : list)
        {
            if (node.getText().equals(id)) {
                Element stu = node.getParent();
                Element student = stu.getParent();
                student.remove(stu);
            }
        }

        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    //查询,根据id查询
    public static Student getStu(String id) throws DocumentException {
        /**
         * 1.创建解析器
         * 2.得到document
         *
         * 3.所有的id
         * 4.返回的是list结合,遍历list集合
         * 5.得到每一个id的节点
         * 6.判断id的值和传递的id是否相同
         * 7.如果相同,先获取id的父节点stu
         * 8.通过stu获取name age的值
         */
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        List<Node> list = document.selectNodes("//id");
        for (Node node : list) {
            if (node.getText().equals(id)) {
                Element stu = node.getParent();
                String name = stu.element("name").getText();
                String age = stu.element("age").getText();
                return new Student(id,name,age);
            }
        }
        return null;
    }
}
