package com.example.employee_management.service;

import java.time.LocalDate;
import java.util.List;

import com.example.employee_management.model.Salary;

public interface SalaryService {
    List<Salary> findAll();
    List<Salary> findByEmpNo(Integer empNo);
    Salary findSalaryByEmpNoAndFromDate(Integer empNo, LocalDate fromDate);
    Salary save(Salary salary);
    void deleteSalary(Integer empNo, LocalDate fromDate);
}
