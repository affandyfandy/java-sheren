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
import com.example.employee_management.model.Title;
import com.example.employee_management.service.EmployeeService;
import com.example.employee_management.service.TitleService;

@RestController
@RequestMapping("/api/v1/title")
public class TitleController {
    
    private final TitleService titleService;
    private final EmployeeService employeeService;

    public TitleController(TitleService titleService, EmployeeService employeeService) {
        this.titleService = titleService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Title>> getAllTitles() {
        List<Title> titles = titleService.findAll();
        return ResponseEntity.ok(titles);
    }

    @GetMapping("/{empNo}")
    public ResponseEntity<List<Title>> getTitlesByEmpNo(@PathVariable Integer empNo) {
        List<Title> titles = titleService.findByEmpNo(empNo);
        if (titles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(titles);
    }
    
    @GetMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<Title> getTitleByEmpNoAndTitleAndFromDate(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
        Title titles = titleService.findTitleByEmpNoAndTitleAndFromDate(empNo, title, fromDate);
        if (titles == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(titles);
    }

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody Title title) {
        Integer empNo = title.getId().getEmpNo();
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.badRequest().body("Invalid employee ID");
        }
        title.setEmployee(employee);
        Title savedTitle = titleService.save(title);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTitle);
    } 

    @PutMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<?> updateTitle(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestBody Title titleDetails) {
        Title titles = titleService.findTitleByEmpNoAndTitleAndFromDate(empNo, title, fromDate);
        if (titles == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title record not found");
        }
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee ID");
        }
        titles.setToDate(titleDetails.getToDate());
        titles.setEmployee(employee);
        Title updatedTitle = titleService.save(titles);
        return ResponseEntity.ok(updatedTitle);
    }
    
    @DeleteMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<?> deleteTitle(
            @PathVariable Integer empNo,
            @PathVariable String title,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
        Title titles = titleService.findTitleByEmpNoAndTitleAndFromDate(empNo, title, fromDate);
        if (titles == null) {
            return ResponseEntity.notFound().build();
        }
        titleService.deleteTitle(empNo, title, fromDate);
        return ResponseEntity.ok("Title record deleted successfully.");
    }
}

