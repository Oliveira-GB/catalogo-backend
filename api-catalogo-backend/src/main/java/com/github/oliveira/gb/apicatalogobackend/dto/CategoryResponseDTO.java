package com.github.oliveira.gb.apicatalogobackend.dto;

import java.util.UUID;

public record CategoryResponseDTO (
        UUID ud,
        String name
){}
