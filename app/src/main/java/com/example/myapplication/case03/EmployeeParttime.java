package com.example.myapplication.case03;

public class EmployeeParttime extends Employee {

    public EmployeeParttime() {
        super();
    }

    public EmployeeParttime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 150.0;
    }

    @Override
    public String toString() {
        return super.toString() + " --> PartTime=" + tinhLuong();
    }
}
