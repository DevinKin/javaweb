package cn.devinkin.onlinehd.dao;

import cn.devinkin.onlinehd.domain.MyFile;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyFileDao {
    private QueryRunner qr = new TxQueryRunner();

    public void add(MyFile myFile) {
        String sql = "INSERT INTO tb_files VALUES(?,?,?,?,?,?)";
        Object[] params = {myFile.getFid(), myFile.getFilepath(),
                myFile.getFramename(), myFile.getCnt(),
                myFile.getFilesize(), myFile.getUploadtime()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MyFile> findAll() {
        String sql = "SELECT * FROM tb_files";
        try {
            return qr.query(sql, new BeanListHandler<MyFile>(MyFile.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Edit(String fid, Integer cnt) {
        String sql = "UPDATE tb_files SET cnt = ? WHERE fid = ?";
        Object[] params = {cnt, fid};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MyFile load(String fid) {
        String sql = "SELECT * FROM tb_files WHERE fid = ?";
        Object[] params = {fid};
        try {
            MyFile myFile = qr.query(sql, new BeanHandler<MyFile>(MyFile.class), params);
            if (myFile != null) {
                Edit(fid, myFile.getCnt() + 1);
            }
            return myFile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
