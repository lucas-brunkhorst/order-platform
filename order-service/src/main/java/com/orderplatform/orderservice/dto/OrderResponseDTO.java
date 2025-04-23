package com.orderplatform.orderservice.dto;

import com.orderplatform.orderservice.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID publicId,
        OrderStatus status,
        List<OrderItemResponseDTO> items
) {}

