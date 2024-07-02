package com.example.assignment3q2;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeService {
    private final String id;

    public RequestScopeService() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
