package cn.devinkin.pcconnect.domain;

public class City {
    private int cid;
    private String cname;

    //该数据丢失
    private Province province;      //多方关联一方

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return cname;
    }

    public void setName(String cname) {
        this.cname = cname;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "City{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", province=" + province +
                '}';
    }
}
