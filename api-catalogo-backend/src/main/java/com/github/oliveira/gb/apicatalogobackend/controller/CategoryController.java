package com.github.oliveira.gb.apicatalogobackend.controller;

import com.github.oliveira.gb.apicatalogobackend.dto.CategoryRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController implements GenericHeaderLocation {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> salvar(@RequestBody @Valid CategoryRequestDTO dto){

        var categorySalva = categoryService.salvar(dto);

        URI location = gerarHeaderLocation(categorySalva.id());

        return ResponseEntity.created(location).body(categorySalva);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> listarTodasCategorias(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable){
        var listagem = categoryService.listarTodasCategorias(pageable);
        return ResponseEntity.ok(listagem);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponseDTO> exibirPorId(UUID id){
        return ResponseEntity.ok(categoryService.obterPorId(id));
    }
}
