package com.example.invoice.service;

import java.util.Optional;
import java.util.UUID;

import com.example.invoice.dto.InvoiceDTO;

public interface InvoiceServiceFeignClient {

    Optional<InvoiceDTO> getInvoiceByIdUsingFeignClient(UUID id);

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    Optional<InvoiceDTO> editInvoice(UUID id, InvoiceDTO invoiceDTO);
}
