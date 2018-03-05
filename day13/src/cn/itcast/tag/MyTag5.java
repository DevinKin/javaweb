package cn.itcast.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 有属性的标签
 * @author king
 */
public class MyTag5 extends SimpleTagSupport {
    private boolean test;

    /**
     * 这个方法由Tomcat来调用,并且在doTag()之前调用
     * @param test
     */
    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (test) {
            /**
             * 执行标签体
             */
            //如果传递的输出流为null,表示使用的就是当前页面的out!
            this.getJspBody().invoke(null);
        }
    }
}
