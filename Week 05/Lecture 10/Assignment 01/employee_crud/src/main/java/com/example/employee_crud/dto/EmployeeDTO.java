package com.example.employee_crud.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String department;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Pattern(regexp = "^\\+62\\d{9,13}$", message = "Phone must be a valid Indonesian number starting with +62")
    private String phone;
}
