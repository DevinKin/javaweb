package cn.itcast.demo4;

import cn.itcast.jdbcUtils.JdbcUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;

/**
 *  大数据
 *  @author king
 */
public class Demo4 {
    /**
     * 把map3保存到数据库中。
     */
    @Test
    public void func1() throws SQLException, IOException {
        /**
         * 1. 得到Connection
         * 2. 给出sql模版，创建pstmt
         * 3. 设置sql模版中的参数
         * 4. 调用pstmt的executeUpdate()执行
         */
        Connection con = JdbcUtils.getConnection();
        String sql = "INSERT INTO tab_bin VALUE(?,?,?) ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,1);
        pstmt.setString(2,"喜欢你.mp3");
        /**
         * 需要Blob
         * 1.我们有的是文件，目标是Blob
         * 2. 先把文件变成byte[]
         * 3. 再使用byte[]创建Blob
         */
        Blob blob = null;
        byte[] bytes = IOUtils.toByteArray(new FileInputStream("E:/BaseServlet1.avi"));
        blob = new SerialBlob(bytes);
        pstmt.setBlob(3, blob);
        pstmt.executeUpdate();
    }

    /**
     * 从数据库读取mp3
     */
    @Test
    public void func2() throws SQLException, IOException {
        /**
         * 1. 创建Connection
         */
        Connection con = JdbcUtils.getConnection();

        /**
         * 2. 给出select语句模版，创建pstmt
         */
        String sql = "SELECT * FROM tab_bin";
        PreparedStatement pstmt = con.prepareStatement(sql);

        /**
         * 3. pstmt执行查询，得到ResultSet
         */
        ResultSet rs = pstmt.executeQuery();

        /**
         * 4. 获取rs中名为data的列数据
         */
        if (rs.next()) {
            Blob blob = rs.getBlob("data");
            /**
             * 把Blob变成硬盘的上的文件！
             * 1.通过Blob得到输入流对象
             * 2.自己创建输出流对象
             * 3.把输入流数据写入输出流
             */
            InputStream inputStream = blob.getBinaryStream();
            OutputStream output = new FileOutputStream("d:/xihuanni.mp3");
            IOUtils.copy(inputStream,output);
        }

    }
}
