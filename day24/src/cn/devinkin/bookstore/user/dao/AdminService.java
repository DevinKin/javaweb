package cn.devinkin.bookstore.user.dao;

import cn.devinkin.bookstore.user.domain.Admin;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    public Admin findByUsername(String adminname) {
        return adminDao.findByUsername(adminname);
    }
}
