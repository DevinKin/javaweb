package cn.devinkin.dbUtils;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo2 {
    @Test
    public void func1() {
        //Stu stu = new Stu(1,"zhangsan",12,"女");
        //addStu(stu);

        Stu s = find(1);
        System.out.println(s);
    }


    public void addStu(Stu stu) {
        QR qr = new QR(jdbcUtils.getDataSource());
        String sql = "INSERT INTO t_stu VALUES(?,?,?,?)";
        Object[] params = {stu.getSid(), stu.getSname(), stu.getAge(), stu.getGender()};
        qr.update(sql, params);
    }

    public Stu find(int sid) {
        QR qr = new QR(jdbcUtils.getDataSource());
        String sql = "SELECT * FROM t_stu WHERE sid=?";
        Object[] params = {sid};

        RsHandler<Stu> rh = new RsHandler<Stu>() {
            @Override
            public Stu handle(ResultSet rs) throws SQLException {
                if(!rs.next())
                    return null;
                Stu stu = new Stu();
                stu.setSid(rs.getInt("sid"));
                stu.setSname(rs.getString("sname"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                return stu;
            }
        };
        return (Stu)qr.query(sql, rh, params);
    }
}
