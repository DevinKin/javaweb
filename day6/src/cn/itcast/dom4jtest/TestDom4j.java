package cn.itcast.dom4jtest;

import cn.itcast.utils.Dom4jUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class TestDom4j {
    public static void main(String[] args) throws Exception {
        //addSchool();
        //delSchool();
        getValues();
    }

    // 查询xml中所有name元素的值
    public static void selectName() throws Exception{
        /**
         * 1.创建解析器
         * 2.得到document
         * 3.得到根节点
         *
         * 4.得到p1
         * 5.得到p1下面的name
         * 6.得到name里面的值
         */
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        Element root = document.getRootElement();

        List<Element> list = root.elements("p1");
        for (Element element : list)
        {
            Element name1 = element.element("name");
            System.out.println(name1.getText());
        }
    }

    public static void select2Name() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Dom4jUtils.PATH);

        Element root = document.getRootElement();

        Element element1 = root.element("p1");
        System.out.println(element1.element("name").getText());
    }

    public static void select3Name() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Dom4jUtils.PATH);

        Element root = document.getRootElement();

        List<Element> list = root.elements("p1");

        System.out.println(list.get(1).element("name").getText());
    }

    public static void addSex() throws Exception {
        /**
         * 1. 创建解析器
         * 2. 得到document
         * 3. 得到根节点
         *
         * 4. 获取到第一个p1
         * 5. p1下面添加元素
         * 6. 在添加完成之后的元素添加文本
         *
         * 7. 回写xml
         */
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(Dom4jUtils.PATH);

        Element element = document.getRootElement();

        Element p1 = element.element("p1");

        p1.addElement("sex").addText("girl");

        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(Dom4jUtils.PATH), OutputFormat.createPrettyPrint()); // 可以有缩进的效果
        xmlWriter.write(document);
        xmlWriter.close();
    }

    // 在第一个p1下面age标签之前添加school标签
    public static void addSchool() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Dom4jUtils.PATH);

        Element root = document.getRootElement();

        Element p1 = root.element("p1");

        List<Element> list = p1.elements();
        Element school = DocumentHelper.createElement("school");
        school.setText("dongxiu.cn");
        list.add(1, school);

        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    //修改年龄
    public static void modifyAge(String value) throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        Element root = document.getRootElement();

        Element p1 = root.element("p1");

        Element age = p1.element("age");
        age.setText(value);
        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    //删除第一个p1下面的school
    public static void delSchool() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        Element root = document.getRootElement();

        Element p1 = root.element("p1");
        Element school = p1.element("school");
        p1.remove(school);

        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    // 获取第一个p1里面的属性id1的值
    public static void getValues() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        Element root = document.getRootElement();

        Element p1 = root.element("p1");
        //System.out.println(p1.attribute("id1").getText());
        System.out.println(p1.attributeValue("id1"));
    }


}
