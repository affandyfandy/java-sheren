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
        return new Employee("A1", "Sheren", 19, employeeWork());
    }   
}
