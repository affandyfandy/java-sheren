package com.example.invoice.service.impl;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.dto.ProductDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.mapper.InvoiceMapper;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceServiceWebClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceWebClientImpl implements InvoiceServiceWebClient {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public InvoiceServiceWebClientImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, WebClient.Builder webClientBuilder) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.webClientBuilder = webClientBuilder;
    }

    public Optional<InvoiceDTO> getInvoiceByIdUsingWebClient(UUID id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);

        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();

            InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);

            List<ProductDTO> products = invoice.getProductIds().stream()
                    .map(productId -> webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8081/api/v1/products/" + productId)
                            .retrieve()
                            .bodyToMono(ProductDTO.class)
                            .block())
                    .collect(Collectors.toList());

            invoiceDTO.setProducts(products);

            return Optional.of(invoiceDTO);
        }

        return Optional.empty();
    }
}
