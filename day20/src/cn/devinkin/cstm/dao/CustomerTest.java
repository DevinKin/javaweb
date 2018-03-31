package cn.devinkin.cstm.dao;

import cn.devinkin.cstm.domain.Customer;
import cn.itcast.utils.CommonUtils;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void func1() {
        CustomerDao dao = new CustomerDao();
        for (int i = 1; i <= 300; i++) {
            Customer c = new Customer();
            c.setCid(CommonUtils.uuid());
            c.setCname("cstm_" + i);
            c.setBirthday("2018-3-19");
            c.setGender(i%2 == 0?"男":"女");
            c.setCellphone("139123" + i);
            c.setEmail("cstm_" + i + "@163.com");
            c.setDescription("我是客户");
            dao.add(c);
        }

    }
}
