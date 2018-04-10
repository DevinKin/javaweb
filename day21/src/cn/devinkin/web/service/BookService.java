package cn.devinkin.web.service;

import cn.devinkin.web.dao.BookDao;
import cn.devinkin.web.domain.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> findAll(){
        return bookDao.findAll();
    }

    public List<Book> findByCategory(int cid){
        return bookDao.findByCategory(cid);
    }
}
