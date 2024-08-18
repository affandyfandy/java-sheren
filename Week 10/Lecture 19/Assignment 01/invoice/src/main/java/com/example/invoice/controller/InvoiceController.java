package com.example.invoice.controller;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.service.InvoiceServiceFeignClient;
import com.example.invoice.service.InvoiceServiceRestTemplate;
import com.example.invoice.service.InvoiceServiceWebClient;
import com.example.invoice.service.impl.InvoiceServiceFeignClientImpl;
import com.example.invoice.service.impl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;
    private final InvoiceServiceFeignClient invoiceServiceFeignClient;
    private final InvoiceServiceRestTemplate invoiceServiceRestTemplate;
    private final InvoiceServiceWebClient invoiceServiceWebClient;

    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceService,
                             InvoiceServiceFeignClientImpl invoiceServiceFeignClient,
                             InvoiceServiceRestTemplate invoiceServiceRestTemplate,
                             InvoiceServiceWebClient invoiceServiceWebClient) {
        this.invoiceService = invoiceService;
        this.invoiceServiceFeignClient = invoiceServiceFeignClient;
        this.invoiceServiceRestTemplate = invoiceServiceRestTemplate;
        this.invoiceServiceWebClient = invoiceServiceWebClient;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/feign-client/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceByIdUsingFeignClient(@PathVariable UUID id) {
        Optional<InvoiceDTO> invoiceDTO = invoiceServiceFeignClient.getInvoiceByIdUsingFeignClient(id);
        return invoiceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rest/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceByIdUsingRestTemplate(@PathVariable UUID id) {
        Optional<InvoiceDTO> invoiceDTO = invoiceServiceRestTemplate.getInvoiceByIdUsingRestTemplate(id);
        return invoiceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/web-client/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceByIdUsingWebClient(@PathVariable UUID id) {
        Optional<InvoiceDTO> invoiceDTO = invoiceServiceWebClient.getInvoiceByIdUsingWebClient(id);
        return invoiceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> addInvoice(@RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        InvoiceDTO addedInvoice = invoiceServiceFeignClient.addInvoice(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> editInvoice(@PathVariable UUID id, @RequestBody InvoiceDTO invoiceDTO) {
        Optional<InvoiceDTO> editedInvoice = invoiceServiceFeignClient.editInvoice(id, invoiceDTO);
        return editedInvoice.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable UUID id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok("Invoice successfully deleted.");
    }

}
