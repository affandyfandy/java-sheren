package com.example.assignment3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "api")
@Data
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "api_key")
    private String key;

    @Column(name = "username")
    private String username;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

}
