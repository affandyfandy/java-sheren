package com.example.product.service;

import com.example.product.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(UUID id);

    Product createProduct(Product product);

    Optional<Product> updateProduct(UUID id, Product product);

    void deleteProduct(UUID id);
}
