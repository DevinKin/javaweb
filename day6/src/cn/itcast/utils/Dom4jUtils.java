package cn.itcast.utils;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;

public class Dom4jUtils {
    public static final String PATH = "src/p1.xml";

    // 返回document
    public static Document getDocument(String Path) throws Exception {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(Path);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 回写xml的方法
    public static void xmlWriters(String url, Document document) throws Exception {
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(url), OutputFormat.createPrettyPrint()); // 可以有缩进的效果
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
