package com.example.assignment3q2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment3q2.service.EmailService;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/api")
public class RequestScopeController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/request-scope")
    public String getRequestScope() {
        String uniqueMessage = "Email sent at: " + java.time.LocalDateTime.now();
        emailService.sendEmail("example@example.com", "Request Scope Test", uniqueMessage);
        return "Request scope: " + uniqueMessage;
    }
}
