package com.example.assignment1.entity;

public class Employee {
    private String id;
    private String name;
    private int age;
    private EmployeeWork employeeWork;

    public Employee(String name, EmployeeWork employeeWork) {
        this.name = name;
        this.employeeWork = employeeWork;
    }

    public Employee(String id, String name, int age, EmployeeWork employeeWork) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        employeeWork.work();
    }

    // Getter and setter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeWork getEmployeeWork() {
        return employeeWork;
    }

    public void setEmployeeWork(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
    }
}
