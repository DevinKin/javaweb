package cn.devinkin.demo1;

import java.util.ArrayList;
import java.util.List;

public class Province {
    //省名
    private String name;
    private List<City> cities = new ArrayList<City>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }
}
