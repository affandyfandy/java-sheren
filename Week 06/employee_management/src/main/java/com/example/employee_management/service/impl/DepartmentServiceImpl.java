package com.example.employee_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management.exception.DepartmentAlreadyExistsException;
import com.example.employee_management.model.Department;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(String deptNo) {
        Optional<Department> department = departmentRepository.findById(deptNo);
        return department.orElse(null);
    }

    @Override
    public Department save(Department department) {
        Optional<Department> existingDepartment = departmentRepository.findById(department.getDeptNo());
        if (existingDepartment.isPresent() && !existingDepartment.get().getDeptNo().equals(department.getDeptNo())) {
            throw new DepartmentAlreadyExistsException(department.getDeptNo());
        }
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(String deptNo) {
        departmentRepository.deleteById(deptNo);
    }
}
