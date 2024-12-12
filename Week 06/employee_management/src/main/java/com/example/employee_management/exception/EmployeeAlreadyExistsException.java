package com.example.employee_management.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(Integer empNo) {
        super("Employee with empNo " + empNo + " already exists."); 
    }
}
