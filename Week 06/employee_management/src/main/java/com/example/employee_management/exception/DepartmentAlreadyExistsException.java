package com.example.employee_management.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
    public DepartmentAlreadyExistsException(String deptNo) {
        super("Department with deptNo " + deptNo + " already exists.");
    }
}
