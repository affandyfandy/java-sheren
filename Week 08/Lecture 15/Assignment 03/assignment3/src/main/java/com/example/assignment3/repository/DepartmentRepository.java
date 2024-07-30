package com.example.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment3.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
