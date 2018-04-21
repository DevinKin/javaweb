package cn.devinkin.web.servlet;

import cn.itcast.utils.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadServlet3")
public class UploadServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 上传三步
         */
        //工厂
        DiskFileItemFactory factory = new DiskFileItemFactory(20 * 1024, new File("/tmp/fileupload"));
        //解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
//        //限制单个文件大小为100K
//        sfu.setFileSizeMax(100 * 1024);     //限制单个文件大小为100K
        sfu.setSizeMax(1024*1024);          //限制整个表单大小为1M
        //解析得到List
        try {
            List<FileItem> list =  sfu.parseRequest(request);
            FileItem fi = list.get(1);

            // 得到根路径
            /**
             * 1.得到文件保存的根目录
             */
            String root = this.getServletContext().getRealPath("/WEB-INF/files");
            /**
             * 2. 生成两层目录
             * (1)得到文件名称
             * (2)得到hashCode
             * (3)转换成十六进制
             * (4)获取前两个字符来生成
             */
            /**
             * 处理文件名的绝对路径问题
             */
            String filename = fi.getName();
            int index = filename.lastIndexOf("/");
            if (index != -1) {
                filename = filename.substring(index+1);
            }
            /**
             * 给文件名添加uuid前缀，处理文件同名问题
             */
            String saveName = CommonUtils.uuid() + "_" + filename;

            /**
             * 1. 得到hashCode
             */
            int hCode = saveName.hashCode();
            String hex = Integer.toHexString(hCode);

            /**
             * 2. 获取hex的前两个字母与root连接在一起生成一个完整的路径
             */
            File dirFile = new File(root,hex.charAt(0) + "/" + hex.charAt(1));
            /**
             * 3. 创建目录链
             */
            dirFile.mkdirs();

            /**
             * 4. 创建目标文件
             */
            File destFile = new File(dirFile, saveName);
            fi.write(destFile);

        } catch (FileUploadException e) {
            if (e instanceof FileUploadBase.SizeLimitExceededException) {
                request.setAttribute("msg", "你上传的文件超出了100KB");
                request.getRequestDispatcher("/form/form3.jsp").forward(request, response);
                System.out.println(request.getAttribute("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
