package com.example.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class EmployeeServiceField {
    @Autowired
    private EmailService emailService;

    public void notifyEmployee(String employeeEmail) {
        System.out.println("This uses field-based DI.");
        emailService.sendEmail(employeeEmail, "New assignment", "There are new assignments for lecture 7.\n");
    }
}
