package com.example.assignment3q2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RequestScopeController {
    @Autowired
    private RequestScopeService requestScopeService;

    @GetMapping("/request-scope")
    public String getRequestScope() {
        return "Request scoped bean ID: " + requestScopeService.getId();
    }
}
