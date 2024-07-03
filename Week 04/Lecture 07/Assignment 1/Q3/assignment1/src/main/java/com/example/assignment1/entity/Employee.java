package com.example.assignment1.entity;

public class Employee {
    private String id;
    private String name;
    private int age;
    private EmployeeWork employeeWork;

    public Employee() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmployeeWork(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        employeeWork.work();
    }
}

// Field-based DI:

// public class Employee {
//     private String id;
//     private String name;
//     private int age;

//     @Autowired
//     private EmployeeWork employeeWork;

//     public Employee() {
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setAge(int age) {
//         this.age = age;
//     }

//     public void working() {
//         System.out.println("Id: " + id);
//         System.out.println("Name: " + name);
//         System.out.println("Age: " + age);
//         employeeWork.work();
//     }
// }
