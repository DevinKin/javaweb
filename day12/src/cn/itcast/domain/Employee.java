package cn.itcast.domain;

public class Employee {
    private String name;
    private double salary;
    private Address addr;

    public String getName() {
        return name;
    }

    public Address getAddr() {
        return addr;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [" + "name=" + name + ", salary=" + salary + ", address=" + addr + "]";
    }

    public String getHehe() {
        return "我去...";
    }
}
