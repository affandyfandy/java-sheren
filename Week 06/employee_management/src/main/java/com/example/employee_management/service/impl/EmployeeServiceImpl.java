package com.example.employee_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.employee_management.exception.EmployeeAlreadyExistsException;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer empNo) {
        Optional<Employee> employee = employeeRepository.findById(empNo);
        return employee.orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getEmpNo() != null) {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getEmpNo());
            if (existingEmployee.isPresent() && !existingEmployee.get().getEmpNo().equals(employee.getEmpNo())) {
                throw new EmployeeAlreadyExistsException(employee.getEmpNo());
            }
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer empNo) {
        employeeRepository.deleteById(empNo);
    }

    @Override
    public Page<Employee> findPaginated(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> searchEmployees(Specification<Employee> spec, Pageable pageable) {
        return employeeRepository.findAll(spec, pageable);
    }
}
