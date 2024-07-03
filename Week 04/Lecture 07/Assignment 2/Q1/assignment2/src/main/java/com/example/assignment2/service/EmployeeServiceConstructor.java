package com.example.assignment2.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceConstructor {
    private final EmailService emailService;

    public EmployeeServiceConstructor(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String employeeEmail) {
        System.out.println("This uses constructor DI.");
        emailService.sendEmail(employeeEmail, "New assignment", "There are new assignments for lecture 7. \n");
    }
}
