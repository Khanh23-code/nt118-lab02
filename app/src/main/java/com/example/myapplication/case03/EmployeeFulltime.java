package com.example.myapplication.case03;

public class EmployeeFulltime extends Employee {

    public EmployeeFulltime() {
        super();
    }

    public EmployeeFulltime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 500.0;
    }

    @Override
    public String toString() {
        return super.toString() + " --> FullTime=" + tinhLuong();
    }
}
