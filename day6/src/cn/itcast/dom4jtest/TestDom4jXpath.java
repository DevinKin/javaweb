package cn.itcast.dom4jtest;

import cn.itcast.utils.Dom4jUtils;
import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;


public class TestDom4jXpath {
    public static void main(String[] args) throws Exception {
        //test1();
        getP1Name();
    }

    //查询xml所有name元素的值
    public static void test1() throws Exception {
        /**
         * 1.得到document
         * 2.直接使用selectNodes方法得到所有的name元素
         */
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        List<Node> list = document.selectNodes("//name");
        for (Node node : list)
        {
            System.out.println(node.getText());
        }
    }

    public static void getP1Name() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        Node p1Name = document.selectSingleNode("//p1[@id1='aaaa']/name");
        System.out.println(p1Name.getText());
    }
}
