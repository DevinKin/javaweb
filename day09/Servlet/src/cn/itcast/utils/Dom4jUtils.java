package cn.itcast.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Dom4jUtils {
    public static final String PATH = "web/WEB-INF/web.xml";

    public static Document getDocument(String path) throws DocumentException {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(Dom4jUtils.PATH);
            return document;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
