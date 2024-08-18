package com.example.product.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;
}
