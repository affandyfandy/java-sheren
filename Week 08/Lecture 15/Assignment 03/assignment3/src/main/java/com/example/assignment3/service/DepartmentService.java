package com.example.assignment3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment3.entity.Department;
import com.example.assignment3.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(String deptNo) {
        Optional<Department> department = departmentRepository.findById(deptNo);
        return department.orElse(null);
    }

    public Department save(Department department) {
        Optional<Department> existingDepartment = departmentRepository.findById(department.getDeptNo());
        return departmentRepository.save(department);
    }

    public void deleteById(String deptNo) {
        departmentRepository.deleteById(deptNo);
    }
}
