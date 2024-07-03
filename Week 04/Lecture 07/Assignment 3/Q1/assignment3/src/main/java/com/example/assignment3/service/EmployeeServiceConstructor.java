package com.example.assignment3.service;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;

@Service
@Scope("prototype")
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
