package com.example.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management.model.Salary;
import com.example.employee_management.model.Salary.SalaryId;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId>{
    List<Salary> findByEmployeeEmpNo(Integer empNo);
}