package com.example.assignment1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.assignment1.entity.Employee;
import com.example.assignment1.entity.EmployeeWork;

@Configuration
public class AppConfig {
    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setId("A1");
        employee.setName("Sheren");
        employee.setAge(19);
        employee.setEmployeeWork((employeeWork()));
        return employee;
    }   
}

// Field-based DI:

// @Configuration
// public class AppConfig {
//     @Bean
//     public EmployeeWork employeeWork() {
//         return new EmployeeWork();
//     }

//     @Bean
//     public Employee employee() {
//         Employee employee = new Employee();
//         employee.setId("A1");
//         employee.setName("Sheren");
//         employee.setAge(19);
        
//         return employee;
//     }   
// }
