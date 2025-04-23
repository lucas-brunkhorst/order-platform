package com.orderplatform.orderservice.dto;

public record OrderItemRequestDTO(
        Long productId,
        Integer quantity
) {}