package com.example.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, name = "id")
    private String id;

    @Column(name = "username", unique = true)
    @NonNull
    private String username;

    @Column(name = "email", unique = true)
    @NonNull
    private String email;

    @NonNull
    @JsonIgnore
    private String password;
}

