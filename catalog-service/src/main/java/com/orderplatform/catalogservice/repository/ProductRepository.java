package com.orderplatform.catalogservice.repository;

import com.orderplatform.catalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByPublicId(UUID publicId);
}