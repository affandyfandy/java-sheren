package com.example.invoice.service.impl;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.dto.ProductDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceServiceRestTemplate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceRestTemplateImpl implements InvoiceServiceRestTemplate {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public InvoiceServiceRestTemplateImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, RestTemplate restTemplate) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.restTemplate = restTemplate;
    }

    public Optional<InvoiceDTO> getInvoiceByIdUsingRestTemplate(UUID id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);

        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();

            InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);

            List<ProductDTO> products = invoice.getProductIds().stream()
                    .map(productId -> restTemplate.getForObject("http://localhost:8081/api/v1/products/" + productId, ProductDTO.class))
                    .collect(Collectors.toList());

            invoiceDTO.setProducts(products);

            return Optional.of(invoiceDTO);
        }

        return Optional.empty();
    }
}