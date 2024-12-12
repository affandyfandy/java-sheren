package com.example.assignment2.service;

import java.util.List;

import com.example.assignment2.model.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String theId);

    void save(Employee theEmployee);

    void deleteById(String theId);

    void saveAll(List<Employee> employees);
}
