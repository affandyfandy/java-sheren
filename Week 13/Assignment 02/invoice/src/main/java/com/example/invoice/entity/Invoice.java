package com.example.invoice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "invoice_products", joinColumns = @JoinColumn(name = "invoice_id"))
    @Column(name = "product_id", nullable = false)
    private List<UUID> productIds;
}
