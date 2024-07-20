package com.example.employee_management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.employee_management.exception.EmployeeAlreadyExistsException;
import com.example.employee_management.model.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Integer empNo);
    Employee save(Employee employee) throws EmployeeAlreadyExistsException;
    void deleteById(Integer empNo);
    Page<Employee> findPaginated(Pageable pageable);
    Page<Employee> searchEmployees(Specification<Employee> spec, Pageable pageable);
}
