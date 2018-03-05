package cn.itcast.jaxptest;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

/**
 * 实现jaxp操作xml
 * @author DevinKin
 */
public class TestJaxp {
    public static void main(String[] args) throws Exception {
        printAll();
    }

    /**
     * 查询第一个name元素的值
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void selectSin() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        Document document = builder.parse("src/person.xml");

        NodeList nodes = document.getElementsByTagName("name");
        Node node = nodes.item(0);
        System.out.println(node.getTextContent());


    }

    /**
     * 查询xml中第一个name元素的值
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static void selectAll() throws ParserConfigurationException, SAXException, IOException {
        // 查询所有name元素的值
        /**
         * 1. 创建解析器工厂
         * 2. 根据解析器工厂创建解析器
         * 3. 解析xml返回document
         * 4. 得到所有的name元素
         * 5. 返回集合，便利集合，得到每一个name元素
         */

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        // 根据解析器工厂创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        // 解析xml返回document
        Document document = builder.parse("src/person.xml");

        // 得到name元素
        NodeList list = document.getElementsByTagName("name");

        for (int i = 0; i < list.getLength(); i++)
        {
            Node name1 = list.item(i);

            // 得到name元素里面的值
            System.out.println(name1.getTextContent());
        }
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void addSex() throws Exception {
        /**
         * 1. 创建解析器工厂
         * 2. 根据解析器工厂创建解析器
         * 3. 解析xml，返回document
         *
         * 4. 得到第一个p1
         * 5. 创建sex标签createElement
         * 6. 创建文本createTextNode
         * 7. 把文本添加到sex下面appendChild
         * 8. 把sex添加到第一个p1下面
         * 9. 回写xml
         */
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse("src/person.xml");

        NodeList list = document.getElementsByTagName("p1");
        Node node = list.item(0);
        Node newNode = document.createElement("sex");
        Node text = document.createTextNode("nv");
        newNode.appendChild(text);
        node.appendChild(newNode);

        // 回写xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));

    }


    /**
     * 修改xml中sex内容为nan
     * @throws Exception
     */
    public static void modifitySex() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse("src/person.xml");

        Node sexNode = document.getElementsByTagName("sex").item(0);
        sexNode.setTextContent("nan");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
    }

    /**
     * 删除sex标签
     * @throws Exception
     */
    public static void removeSex() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse("src/person.xml");


        Node sexNode = document.getElementsByTagName("sex").item(0);

        Node firstp1Node = sexNode.getParentNode();
        firstp1Node.removeChild(sexNode);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
    }


    /**
     * 打印所有元素
     * @throws Exception
     */
    public static void printAll() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse("src/person.xml");

        list1(document);
    }

    private static void list1(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE)
            System.out.println(node.getNodeName());
        NodeList list = node.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {
            Node node1 = list.item(i);
            list1(node1);
        }
    }
}
