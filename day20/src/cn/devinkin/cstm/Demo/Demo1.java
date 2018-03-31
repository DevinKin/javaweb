package cn.devinkin.cstm.Demo;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 我们现在写两个配置文件，一个文件中存放中文信息，另一个存放英文信息
 * 我们使用一个类，来通过语言环境，最终识别加载哪一个文件的信息：ResourceBundle
 * 语言环境：Locale
 * new Locale("zh", "CN");
 * Locale.getDefault();
 * Locale.US
 *
 * 资源文件的名称格式：基本名称+Locale部分+扩展名
 * 例如：res_zh_CN.properties，基本名称：res，Locale部分：zh_CN，扩展名：properties
 * 必须所有的资源文件基本名称都要相同！不同之处就是Locale部件
 */
public class Demo1 {

    @Test
    public void fun1() {
        Locale locale = Locale.CHINA;
        //得到ResourceBundle
        //第一个参数：基本名称，res
        //第二个参数：Locale
        ResourceBundle rb = ResourceBundle.getBundle("res", locale);

        //使用ResourceBundle来获取资源信息
        System.out.println(rb.getString("username"));
        System.out.println(rb.getString("password"));
        System.out.println(rb.getString("login"));
    }
}
