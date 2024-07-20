package com.example.employee_management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management.dto.SearchCriteriaDTO;
import com.example.employee_management.exception.EmployeeAlreadyExistsException;
import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import com.example.employee_management.specification.EmployeeSpecificationBuilder;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{empNo}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer empNo) {
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (EmployeeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    } 

    @PutMapping("/{empNo}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer empNo,
            @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setGender(employeeDetails.getGender());
        employee.setHireDate(employeeDetails.getHireDate());
        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @DeleteMapping("/{empNo}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer empNo) {
        Employee employee = employeeService.findById(empNo);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(empNo);
        return ResponseEntity.ok("Employee record deleted successfully.");
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeesPage = employeeService.findPaginated(pageable);
        return ResponseEntity.ok(employeesPage);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployees(
            @RequestParam Map<String, String> searchParams,
            Pageable pageable) {
        
        List<SearchCriteriaDTO> searchCriteriaDTOList = new ArrayList<>();
        searchParams.forEach((key, value) -> {
            SearchCriteriaDTO dto = new SearchCriteriaDTO(key, ":", value);
            searchCriteriaDTOList.add(dto);
        });

        EmployeeSpecificationBuilder builder = new EmployeeSpecificationBuilder();
        searchCriteriaDTOList.forEach(dto -> {
            builder.with(dto.getKey(), dto.getOperation(), dto.getValue());
        });

        Page<Employee> employeesPage = employeeService.searchEmployees(builder.build(), pageable);
        return ResponseEntity.ok(employeesPage);
    }
    
}
