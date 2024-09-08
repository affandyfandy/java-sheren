package com.example.invoice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private UUID id;
    private LocalDate invoiceDate;
    private List<UUID> productIds;
    private List<ProductDTO> products;
}
