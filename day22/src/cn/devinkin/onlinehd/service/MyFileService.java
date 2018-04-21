package cn.devinkin.onlinehd.service;

import cn.devinkin.onlinehd.dao.MyFileDao;
import cn.devinkin.onlinehd.domain.MyFile;

import java.util.List;

public class MyFileService {
    private MyFileDao myFileDao = new MyFileDao();

    public void add(MyFile myFile) {
        myFileDao.add(myFile);
    }

    public List<MyFile> findAll() {
        return myFileDao.findAll();
    }

    public MyFile download(String fid) {
        return myFileDao.load(fid);
    }
}
