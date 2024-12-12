package com.example.assignment3.response;

import com.example.assignment3.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponse {

    private String username;
    private String timestamp;
    private Department department;

    public DepartmentResponse(String username, String timestamp, Department department) {
        this.username = username;
        this.timestamp = timestamp;
        this.department = department;
    }
}
