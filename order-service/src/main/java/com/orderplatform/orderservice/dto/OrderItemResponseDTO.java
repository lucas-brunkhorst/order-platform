package com.orderplatform.orderservice.dto;

public record OrderItemResponseDTO(
        Long productId,
        Integer quantity
) {}