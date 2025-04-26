package com.orderplatform.catalogservice.service;

import com.orderplatform.catalogservice.dto.ProductRequestDTO;
import com.orderplatform.catalogservice.dto.ProductResponseDTO;
import com.orderplatform.catalogservice.model.Product;
import com.orderplatform.catalogservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO dto) {
        var product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        var productSaved = productRepository.save(product);

        return new ProductResponseDTO(productSaved.getId(), productSaved.getName(),
                productSaved.getDescription(), productSaved.getPrice(), productSaved.getQuantity());
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream().map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getQuantity()))
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProduct(UUID id) {
        var repository = productRepository.findByPublicId(id);

        return new ProductResponseDTO(repository.getId(), repository.getName(),
                repository.getDescription(), repository.getPrice(), repository.getQuantity());

    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id incorreto"));

        existingProduct.setName(dto.name());
        existingProduct.setDescription(dto.description());
        existingProduct.setPrice(dto.price());
        existingProduct.setQuantity(dto.quantity());

        var updated = productRepository.save(existingProduct);
        return new ProductResponseDTO(updated.getId(), updated.getName(), updated.getDescription(),
                updated.getPrice(), updated.getQuantity());
    }
}