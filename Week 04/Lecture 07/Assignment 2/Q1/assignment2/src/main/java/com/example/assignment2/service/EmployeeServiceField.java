package com.example.assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceField {
    @Autowired
    private EmailService emailService;

    public void notifyEmployee(String employeeEmail) {
        System.out.println("This uses field-based DI.");
        emailService.sendEmail(employeeEmail, "New assignment", "There are new assignments for lecture 7.\n");
    }
}
