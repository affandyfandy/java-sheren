package com.example.employee_management.service;

import java.util.List;

import com.example.employee_management.exception.DepartmentAlreadyExistsException;
import com.example.employee_management.model.Department;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(String deptNo);
    Department save(Department department) throws DepartmentAlreadyExistsException;
    void deleteById(String deptNo);
}
