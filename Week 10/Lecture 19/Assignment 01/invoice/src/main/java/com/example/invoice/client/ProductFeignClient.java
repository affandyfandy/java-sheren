package com.example.invoice.client;

import com.example.invoice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "product-service", url = "http://localhost:8081/api/v1/products")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable("id") UUID id);
}

