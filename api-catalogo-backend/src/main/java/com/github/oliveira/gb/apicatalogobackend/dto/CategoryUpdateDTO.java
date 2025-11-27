package com.github.oliveira.gb.apicatalogobackend.dto;

import jakarta.validation.constraints.Size;

public record CategoryUpdateDTO(
        @Size(min = 5, max = 550)
        String name
) {
}
