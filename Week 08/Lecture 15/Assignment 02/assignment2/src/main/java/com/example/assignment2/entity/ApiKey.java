package com.example.assignment2.entity;

import jakarta.persistence.*;
import lombok.Data;

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

}
