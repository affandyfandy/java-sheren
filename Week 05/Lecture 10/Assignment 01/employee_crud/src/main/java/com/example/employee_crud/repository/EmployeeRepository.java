package com.example.employee_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_crud.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, String> {
    List<Employee> findByDepartment(String department);
}