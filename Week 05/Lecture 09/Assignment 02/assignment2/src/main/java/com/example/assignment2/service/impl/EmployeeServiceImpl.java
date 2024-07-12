package com.example.assignment2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.assignment2.model.Employee;
import com.example.assignment2.repository.EmployeeRepository;
import com.example.assignment2.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Employee findById(String theId) {
        return employeeRepository.findById(theId).orElseThrow();
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(String theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
    
}
