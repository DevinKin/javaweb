package cn.icast.jaspsax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TestSax {
    public static void main(String[] args) throws Exception
    {
        /**
         * 1. 创建解析器工厂
         * 2. 创建解析器
         * 3. 执行parse方法
         *
         * 4. 自己创建一个类，这个类集成DefaultHandler
         * 5. 重写类里面的三个方法
         */

        // 创建解析器工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        // 创建解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();

        // 执行parse方法
        saxParser.parse("src/p1.xml", new MyDefault2());
    }
}

/**
 * 实现获取所有name元素的值
 */
class MyDefault2 extends DefaultHandler
{
    private boolean flag = false;
    private int idx = 1;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("name".equals(qName))
            flag =  true;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (flag && idx == 1) {
            System.out.println(new String(ch,start,length));
            idx++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("name".equals(qName))
            flag =  false;
    }
}
class MyDefault1 extends DefaultHandler
{
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.print("<" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.print("<" + qName + ">");
    }
}
