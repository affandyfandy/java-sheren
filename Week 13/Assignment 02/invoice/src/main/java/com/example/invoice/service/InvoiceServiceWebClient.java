package com.example.invoice.service;

import java.util.Optional;
import java.util.UUID;

import com.example.invoice.dto.InvoiceDTO;

public interface InvoiceServiceWebClient {

    Optional<InvoiceDTO> getInvoiceByIdUsingWebClient(UUID id);

}