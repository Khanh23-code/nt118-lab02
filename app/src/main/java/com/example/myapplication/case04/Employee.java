package com.example.myapplication.case04;

public class Employee {
    private String id;
    private String name;
    private boolean isManager;

    public Employee() {
    }

    public Employee(String id, String name, boolean isManager) {
        this.id = id;
        this.name = name;
        this.isManager = isManager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return name;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }
}
