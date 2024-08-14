package com.example.invoice.service;

import java.util.List;
import java.util.UUID;

import com.example.invoice.dto.InvoiceDTO;

public interface InvoiceService {

    List<InvoiceDTO> getAllInvoices();

    void deleteInvoice(UUID id);

}
