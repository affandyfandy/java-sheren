package com.example.webClient.reactive.model;

import com.example.webClient.reactive.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Role role;

    public Employee() {

    }

    public Employee(Integer employeeId, String firstName, String lastName, Integer age, Role role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
    }
}
