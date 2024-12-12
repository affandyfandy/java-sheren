package com.example.employee_management.controller;

import java.util.List;

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

import com.example.employee_management.exception.DepartmentAlreadyExistsException;
import com.example.employee_management.model.Department;
import com.example.employee_management.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{deptNo}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String deptNo) {
        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            Department savedDepartment = departmentService.save(department);
            return ResponseEntity.ok(savedDepartment);
        } catch (DepartmentAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    } 

    @PutMapping("/{deptNo}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable String deptNo,
            @RequestBody Department departmentDetails) {
        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        department.setDeptName(departmentDetails.getDeptName());
        Department updatedDepartment = departmentService.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }
    
    @DeleteMapping("/{deptNo}")
    public ResponseEntity<?> deleteDepartment(@PathVariable String deptNo) {
        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteById(deptNo);
        return ResponseEntity.ok("Department record deleted successfully.");
    }

}
