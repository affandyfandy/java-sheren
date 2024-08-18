package com.example.invoice.service.impl;

import java.util.List;
import java.util.UUID;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDTO).toList();
    }

    @Override
    public void deleteInvoice(UUID id) {
        invoiceRepository.deleteById(id);
    }
}
