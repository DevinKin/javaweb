package cn.devinkin.onlinehd.servlet;

import cn.devinkin.onlinehd.domain.MyFile;
import cn.devinkin.onlinehd.service.MyFileService;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MyFileUploadServlet")
public class MyFileUploadServlet extends HttpServlet {
    private MyFileService myFileService = new MyFileService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 上传三步
         *  1. 获得工厂
         *  2. 获得解析器
         *  3. 解析list
         * 2. 把MyFile通过service保存到数据库中
         *  1. 处理文件绝对路径问题
         *  2. 获取uuid，保存在myFile中
         *  3. 使用LocalDateTime获取时间
         * 3. 目录打散，使用哈希打散
         * 4. 把FileItem对应的文件保存到硬盘
         */
        //1.上传三步
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //限制文件大小为10M
        sfu.setFileSizeMax(1024*1024*2);
        try {
            List<FileItem> list = sfu.parseRequest(request);
            FileItem fi = list.get(0);

            //得到根路径
            String root = this.getServletContext().getRealPath("/WEB-INF/hdfiles");
            /**
             * 1. 目录打散
             */
            //获取myfile
            MyFile myFile = getMyFile(fi);
            String filename = CommonUtils.uuid() + "_" + myFile.getFramename();
            int hCode = filename.hashCode();
            String hex = Integer.toHexString(hCode);
            /**
             * 获取hex前面两个字母与root连接在一起生成一个完成的路径
             */
            File dirFile = new File(root, hex.charAt(0) + "/" + hex.charAt(1));
            //保存文件真实路径
            myFile.setFilepath(dirFile.getPath() + "/" + myFile.getFramename());
            //把myFile保存到数据库
            myFileService.add(myFile);

            //4.保存到硬盘
            dirFile.mkdirs();
            File destFile = new File(dirFile, myFile.getFramename());
            fi.write(destFile);
            request.setAttribute("msg", "上传成功");
            request.getRequestDispatcher("/onlinehd/msg.jsp").forward(request,response);

        } catch (FileUploadException e) {
            System.out.println(e.getClass().getName());
            if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
                request.setAttribute("msg", "你上传的文件超过了2M");
                request.getRequestDispatcher("/onlinehd/msg.jsp").forward(request,response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyFile getMyFile(FileItem it) {
        MyFile myFile = new MyFile();
        String fileName = it.getName();
        /**
         * 1. 处理文件绝对路径问题
         */
        int index = fileName.lastIndexOf("/");
        if (index != -1) {
            fileName = fileName.substring(index + 1);
        }
        myFile.setFid(CommonUtils.uuid());
        myFile.setCnt(0);
        myFile.setFramename(fileName);
        myFile.setFilesize(it.getSize());
        myFile.setUploadtime(LocalDateTime.now().toString());
//        myFile.setFilepath();
        return myFile;
    }
}
