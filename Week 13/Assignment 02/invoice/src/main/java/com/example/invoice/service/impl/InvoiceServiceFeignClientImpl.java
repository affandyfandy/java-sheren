package com.example.invoice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.invoice.client.ProductFeignClient;
import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.dto.ProductDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceFeignClientImpl implements InvoiceServiceFeignClient {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final ProductFeignClient productFeignClient;

    @Autowired
    public InvoiceServiceFeignClientImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, ProductFeignClient productFeignClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public Optional<InvoiceDTO> getInvoiceByIdUsingFeignClient(UUID id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);

        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();

            List<ProductDTO> products = invoice.getProductIds().stream()
                    .map(productFeignClient::getProductById)
                    .collect(Collectors.toList());

            InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);
            invoiceDTO.setProducts(products);

            return Optional.of(invoiceDTO);
        }
        return Optional.empty();
    }

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);

        if (invoice.getProductIds() == null || invoice.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("Product IDs cannot be null or empty");
        }

        invoice = invoiceRepository.save(invoice);

        List<ProductDTO> products = invoiceDTO.getProductIds().stream()
                .map(productFeignClient::getProductById)
                .collect(Collectors.toList());

        InvoiceDTO result = invoiceMapper.toDTO(invoice);
        result.setProducts(products);

        return result;
    }

    @Override
    public Optional<InvoiceDTO> editInvoice(UUID id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> existingInvoice = invoiceRepository.findById(id);

        if (existingInvoice.isPresent()) {
            Invoice invoice = existingInvoice.get();

            invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());

            invoice.getProductIds().clear();
            invoice.getProductIds().addAll(invoiceDTO.getProductIds());

            invoice = invoiceRepository.save(invoice);

            // Fetch the updated product details using FeignClient
            List<ProductDTO> products = invoice.getProductIds().stream()
                    .map(productFeignClient::getProductById)
                    .collect(Collectors.toList());

            InvoiceDTO updatedInvoiceDTO = invoiceMapper.toDTO(invoice);
            updatedInvoiceDTO.setProducts(products);

            return Optional.of(updatedInvoiceDTO);
        } else {
            return Optional.empty();
        }
    }
}
