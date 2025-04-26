package com.orderplatform.catalogservice.controller;

import com.orderplatform.catalogservice.dto.ProductRequestDTO;
import com.orderplatform.catalogservice.dto.ProductResponseDTO;
import com.orderplatform.catalogservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO product) {
        return productService.create(product);
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<ProductResponseDTO> getProduct(UUID id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }
}