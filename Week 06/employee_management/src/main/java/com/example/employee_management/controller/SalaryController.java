package com.example.employee_management.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management.model.Employee;
import com.example.employee_management.model.Salary;
import com.example.employee_management.service.EmployeeService;
import com.example.employee_management.service.SalaryService;

@RestController
@RequestMapping("/api/v1/salary")
public class SalaryController {
    
    private final SalaryService salaryService;
    private final EmployeeService employeeService;

    public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaries = salaryService.findAll();
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/{empNo}")
    public ResponseEntity<List<Salary>> getSalariesByEmpNo(@PathVariable Integer empNo) {
        List<Salary> salaries = salaryService.findByEmpNo(empNo);
        if (salaries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/{empNo}/{fromDate}")
    public ResponseEntity<Salary> getSalaryByEmpNoAndFromDate(
        @PathVariable Integer empNo,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
            Salary salary = salaryService.findSalaryByEmpNoAndFromDate(empNo, fromDate);
        if (salary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salary);
    }

    @PostMapping
    public ResponseEntity<?> createSalary(@RequestBody Salary salary) {
        Integer empNo = salary.getId().getEmpNo();
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.badRequest().body("Invalid employee ID");
        }
        salary.setEmployee(employee);
        Salary savedSalary = salaryService.save(salary);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalary);
    } 

    @PutMapping("/{empNo}/{fromDate}")
    public ResponseEntity<?> updateSalary(
            @PathVariable Integer empNo,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestBody Salary salaryDetails) {
        Salary salary = salaryService.findSalaryByEmpNoAndFromDate(empNo, fromDate);
        if (salary == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary record not found");
        }
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee ID");
        }
        salary.setSalary(salaryDetails.getSalary());
        salary.setToDate(salaryDetails.getToDate());
        salary.setEmployee(employee);
        Salary updatedSalary = salaryService.save(salary);
        return ResponseEntity.ok(updatedSalary);
    }
    
    @DeleteMapping("/{empNo}/{fromDate}")
    public ResponseEntity<?> deleteSalary(
            @PathVariable Integer empNo,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
        Salary salary = salaryService.findSalaryByEmpNoAndFromDate(empNo, fromDate);
        if (salary == null) {
            return ResponseEntity.notFound().build();
        }
        salaryService.deleteSalary(empNo, fromDate);
        return ResponseEntity.ok("Salary record deleted successfully.");
    }

}
