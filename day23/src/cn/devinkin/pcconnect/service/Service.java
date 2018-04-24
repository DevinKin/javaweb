package cn.devinkin.pcconnect.service;

import cn.devinkin.pcconnect.dao.Dao;
import cn.devinkin.pcconnect.domain.City;
import cn.devinkin.pcconnect.domain.Province;

import java.util.List;

public class Service {
    private Dao dao = new Dao();

    public List<Province> findAllProvince() {
        return dao.findAllProvince();
    }

    public List<City> findByProvince(int pid) {
        return dao.findByProvince(pid);
    }
}
