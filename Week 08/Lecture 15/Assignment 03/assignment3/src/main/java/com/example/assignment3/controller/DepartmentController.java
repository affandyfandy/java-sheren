package com.example.assignment3.controller;

import java.util.List;

import com.example.assignment3.response.DeleteResponse;
import com.example.assignment3.response.DepartmentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment3.entity.Department;
import com.example.assignment3.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String timestamp = (String) request.getAttribute("timestamp");

        List<Department> departments = departmentService.findAll();
        List<DepartmentResponse> response = departments.stream()
                .map(department -> new DepartmentResponse(username, timestamp, department))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{deptNo}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable String deptNo, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String timestamp = (String) request.getAttribute("timestamp");

        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        DepartmentResponse response = new DepartmentResponse(username, timestamp, department);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody Department department, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String timestamp = (String) request.getAttribute("timestamp");

        Department savedDepartment = departmentService.save(department);
        DepartmentResponse response = new DepartmentResponse(username, timestamp, savedDepartment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{deptNo}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable String deptNo,
            @RequestBody Department departmentDetails,
            HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String timestamp = (String) request.getAttribute("timestamp");

        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        department.setDeptName(departmentDetails.getDeptName());
        Department updatedDepartment = departmentService.save(department);

        DepartmentResponse response = new DepartmentResponse(username, timestamp, updatedDepartment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{deptNo}")
    public ResponseEntity<DeleteResponse> deleteDepartment(@PathVariable String deptNo, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String timestamp = (String) request.getAttribute("timestamp");

        Department department = departmentService.findById(deptNo);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteById(deptNo);

        DeleteResponse response = new DeleteResponse("Department record deleted successfully.", username, timestamp);
        return ResponseEntity.ok(response);
    }

}
