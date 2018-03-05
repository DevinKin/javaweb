package cn.itcast.tag;


import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;

/**
 * 自定义标签
 * @author king
 */
public class MyTag1 implements SimpleTag {
    private PageContext pageContext;
    private JspFragment jspFragment;

    /**
     * 所有的setXXX()方法都会在doTag()方法之前被tomcat调用
     * 所以在doTag()中就可以使用tomcat传递过来的对象
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        pageContext.getOut().print("Hello Tag");
    }

    @Override
    public void setParent(JspTag jspTag) {

    }

    @Override
    public JspTag getParent() {
        return null;
    }

    @Override
    public void setJspContext(JspContext jspContext) {
        this.pageContext = (PageContext)jspContext;
    }

    @Override
    public void setJspBody(JspFragment jspFragment) {
        this.jspFragment = jspFragment;
    }
}
