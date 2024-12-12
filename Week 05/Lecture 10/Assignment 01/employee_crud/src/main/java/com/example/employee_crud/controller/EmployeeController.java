package com.example.employee_crud.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.employee_crud.dto.EmployeeDTO;
import com.example.employee_crud.exception.InvalidInputException;
import com.example.employee_crud.exception.CsvProcessingException;
import com.example.employee_crud.exception.ResourceNotFoundException;
import com.example.employee_crud.mapper.EmployeeMapper;
import com.example.employee_crud.model.Employee;
import com.example.employee_crud.repository.EmployeeRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listAllEmployee() {
        List<Employee> listEmployee = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = listEmployee.stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                .collect(Collectors.toList());

        if(employeeDTOs.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // Get employee detail by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findEmployee(@PathVariable("id") String id) {
        Optional<Employee> employeeOpt= employeeRepository.findById(id);
        if(employeeOpt.isPresent()) {
            EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(employeeOpt.get());
            return ResponseEntity.ok(employeeDTO);
        }
        throw new ResourceNotFoundException("Employee not found with id: " + id);
    }

    // Create new employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO.getDateOfBirth() == null) {
            throw new InvalidInputException("Date of birth cannot be null");
        }
        if (employeeDTO.getAddress() == null || employeeDTO.getAddress().isEmpty()) {
            throw new InvalidInputException("Address cannot be null or empty");
        }
        if (employeeDTO.getDepartment() == null || employeeDTO.getDepartment().isEmpty()) {
            throw new InvalidInputException("Department cannot be null or empty");
        }

        Employee employee = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(savedEmployee);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    // Update employee by id
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") String id,
                                                      @Valid @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if(employeeOpt.isPresent()) {
            Employee employeeToUpdate = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
            employeeToUpdate.setId(id);
            Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
            EmployeeDTO updatedEmployeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(updatedEmployee);
            return ResponseEntity.ok(updatedEmployeeDTO);
        }
        throw new ResourceNotFoundException("Employee not found with id: " + id);
    }

    // Delete employee by id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") String id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if(employeeOpt.isPresent()) {
            employeeRepository.delete(employeeOpt.get());
            return ResponseEntity.ok().build();

        }
        throw new ResourceNotFoundException("Employee not found with id: " + id);
    }

    // Input csv file
    @PostMapping("/upload-csv")
    public ResponseEntity<List<EmployeeDTO>> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).build())) {

            for (CSVRecord csvRecord : csvParser) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setName(csvRecord.get("Name"));
                employeeDTO.setDateOfBirth(LocalDate.parse(csvRecord.get("DateOfBirth"), dateFormatter));
                employeeDTO.setAddress(csvRecord.get("Address"));
                employeeDTO.setDepartment(csvRecord.get("Department"));
                employeeDTO.setEmail(csvRecord.get("Email"));
                employeeDTO.setPhone(csvRecord.get("Phone"));

                employeeDTOs.add(employeeDTO);
            }

            List<Employee> employees = employeeDTOs.stream()
                    .map(EmployeeMapper.INSTANCE::employeeDTOToEmployee)
                    .collect(Collectors.toList());

            List<Employee> savedEmployees = employeeRepository.saveAll(employees);

            List<EmployeeDTO> savedEmployeeDTOs = savedEmployees.stream()
                    .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(savedEmployeeDTOs);

        } catch (IOException e) {
            throw new CsvProcessingException("Error processing CSV file", e);
        }
    }

    // Return employees by department
    @GetMapping("/department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@RequestParam("department") String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                .collect(Collectors.toList());

        if (!employeeDTOs.isEmpty()) {
            return ResponseEntity.ok(employeeDTOs);
        }
        throw new ResourceNotFoundException("Employee not found with department: " + department);
    }
}