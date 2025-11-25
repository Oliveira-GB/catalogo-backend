package com.github.oliveira.gb.apicatalogobackend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record ProductRequestDTO(

        @NotBlank(message = "É obrigatório ter um nome")
        @Size(min = 5, max = 150, message = "Nome completo não deve ultrapassar 250 caracteres")
        String name,

        @NotBlank(message = "É obrigatório ter uma descrição do produto!!")
        @Size(min = 10, max = 500, message = "Descrição não pode ultrapassar 500 caracteres.")
        String description,

        @Positive(message = "O preço deve ser maior que 0, não é permitido itens gratuiros")
        @Digits(integer = 4, fraction = 2, message = "Valor alto demais ninguem é rico aqui não filho!")
        BigDecimal price,

        @PositiveOrZero(message = "Não é permitido quantidades negativas")
        Integer quantity,

        @NotNull(message = "A lista de Id's da categoria não pode ser nula")
        @NotEmpty(message = "A lista não pode estar vazia")
        Set<UUID> categoryIds
) {}
