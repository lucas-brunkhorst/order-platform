package com.orderplatform.catalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank String name,
        String description,
        @NotNull BigDecimal price,
        @NotNull Integer quantity
) {}
