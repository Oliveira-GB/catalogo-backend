package com.github.oliveira.gb.apicatalogobackend.controller;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController implements GenericHeaderLocation {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> salvar(@RequestBody @Valid ProductRequestDTO dto){
        var productSalvo = productService.salvar(dto);

        URI location = gerarHeaderLocation(productSalvo.id());

        return ResponseEntity.created(location).body(productSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> listarTodosProdutos(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable){
        var listagem = productService.listarTodosProdutos(pageable);
        return ResponseEntity.ok(listagem);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> exibirPorId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(productService.obterPorId(id));
    }
}
