package com.github.oliveira.gb.apicatalogobackend.controller;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductUpdateDTO;
import com.github.oliveira.gb.apicatalogobackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> exibirPorId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(productService.obterPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") UUID id){
        productService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> pesquisar(
            @RequestParam(value = "name", required = false)
            String name,

            @RequestParam(value = "price", required = false)
            BigDecimal price,

            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
            ){
        Page<ProductResponseDTO> resultadoPagina = productService.pesquisar(name, price, pageable);
        return ResponseEntity.ok(resultadoPagina);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> atualizar(@PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateDTO dto){
        var productAtulizado = productService.atualizar(id, dto);
        return ResponseEntity.ok(productAtulizado);
    }
}
