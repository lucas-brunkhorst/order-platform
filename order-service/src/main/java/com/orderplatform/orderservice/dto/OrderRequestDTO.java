package com.orderplatform.orderservice.dto;

import java.util.List;

public record OrderRequestDTO(
        List<OrderItemRequestDTO> items
) {}
