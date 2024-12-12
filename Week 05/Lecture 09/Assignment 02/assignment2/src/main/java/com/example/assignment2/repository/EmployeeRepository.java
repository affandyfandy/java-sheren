package com.example.assignment2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByOrderByNameAsc();
}
