package com.github.oliveira.gb.apicatalogobackend.controller;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController implements GenericHeaderLocation {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> salvar(@RequestBody @Valid ProductRequestDTO dto){
        var productSalvo = productService.salvar(dto);

        URI location = gerarHeaderLocation(productSalvo.id());

        return ResponseEntity.created(location).body(productSalvo);
    }
}
