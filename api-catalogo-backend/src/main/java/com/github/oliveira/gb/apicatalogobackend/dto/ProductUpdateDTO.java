package com.github.oliveira.gb.apicatalogobackend.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        @Size(min = 5, max = 150)
        String name,

        @Size(min = 5, max = 550)
        String description,

        @Positive
        @Digits(integer = 4, fraction = 2)
        BigDecimal price,

        @PositiveOrZero
        Integer quantity
) {
}
