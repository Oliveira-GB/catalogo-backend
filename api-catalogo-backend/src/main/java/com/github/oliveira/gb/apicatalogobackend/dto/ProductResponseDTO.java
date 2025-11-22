package com.github.oliveira.gb.apicatalogobackend.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Set<CategoryResponseDTO> categories
) {}
