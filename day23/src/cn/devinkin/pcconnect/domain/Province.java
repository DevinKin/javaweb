package cn.devinkin.pcconnect.domain;

import java.util.List;

public class Province {
    private int pid;
    private String pname;

    //该数据丢失
    private List<City> cityList;    //一方关联多方

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return pname;
    }

    public void setName(String pname) {
        this.pname = pname;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "Province{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
