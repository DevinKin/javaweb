package cn.devinkin.pcconnect.dao;


import cn.devinkin.pcconnect.domain.City;
import cn.devinkin.pcconnect.domain.Province;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Dao {
    private QueryRunner qr = new TxQueryRunner();
    /**
     * 查询所有的省份
     * @return
     */
    public List<Province> findAllProvince() {
        String sql = "SELECT * FROM t_province";
        try {
            return qr.query(sql, new BeanListHandler<Province>(Province.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询指定省下的所有市
     * @param pid
     * @return
     */
    public List<City> findByProvince(int pid) {
        try {
            String sql = "SELECT * FROM t_city WHERE pid=?";
            return qr.query(sql, new BeanListHandler<City>(City.class), pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
