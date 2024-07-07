package com.example.crud_employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.crud_employee.dao.EmployeeDaoImpl;
import com.example.crud_employee.model.Employee;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeDaoImpl employeeDao;

    // Methods for datasource 1
    @GetMapping("/datasource1")
    public ResponseEntity<List<Employee>> listAllEmployee1() {
        List<Employee> listEmployee = employeeDao.listEmployee1();
        if (listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping(value = "/datasource1/{id}")
    public ResponseEntity<Employee> findEmployee1(@PathVariable("id") String id) {
        Employee employee = employeeDao.getEmployee1(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/datasource1")
    public ResponseEntity<Employee> saveEmployee1(@RequestBody Employee employee) {
        employeeDao.addEmployee1(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/datasource1/{id}")
    public ResponseEntity<Employee> updateEmployee1(@PathVariable("id") String id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeDao.getEmployee1(id);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id); // Ensure the ID is set correctly
        employeeDao.updateEmployee1(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/datasource1/{id}")
    public ResponseEntity<Void> deleteEmployee1(@PathVariable("id") String id) {
        try {
            employeeDao.deleteEmployee1(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Methods for datasource 2
    @GetMapping("/datasource2")
    public ResponseEntity<List<Employee>> listAllEmployee2() {
        List<Employee> listEmployee = employeeDao.listEmployee2();
        if (listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping(value = "/datasource2/{id}")
    public ResponseEntity<Employee> findEmployee2(@PathVariable("id") String id) {
        Employee employee = employeeDao.getEmployee2(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/datasource2")
    public ResponseEntity<Employee> saveEmployee2(@RequestBody Employee employee) {
        employeeDao.addEmployee2(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/datasource2/{id}")
    public ResponseEntity<Employee> updateEmployee2(@PathVariable("id") String id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeDao.getEmployee2(id);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id); // Ensure the ID is set correctly
        employeeDao.updateEmployee2(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/datasource2/{id}")
    public ResponseEntity<Void> deleteEmployee2(@PathVariable("id") String id) {
        try {
            employeeDao.deleteEmployee2(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
