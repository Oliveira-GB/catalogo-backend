package com.github.oliveira.gb.apicatalogobackend.service;

import com.github.oliveira.gb.apicatalogobackend.dto.CategoryRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.exception.RecursoNaoEncontradoException;
import com.github.oliveira.gb.apicatalogobackend.mappers.CategoryMapper;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
import com.github.oliveira.gb.apicatalogobackend.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidator categoryValidator;

    @Transactional
    public CategoryResponseDTO salvar(CategoryRequestDTO dto){
        categoryValidator.validar(dto);

        var categoryEntity = categoryMapper.toEntity(dto);

        categoryEntity = categoryRepository.save(categoryEntity);

        return categoryMapper.toDTO(categoryEntity);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO obterPorId(UUID id){
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

    }

    @Transactional(readOnly = true)
    public Page<CategoryResponseDTO> listarTodasCategorias(
            Pageable pageable){
        Page<Category> resultado = categoryRepository.findAll(pageable);
        Page<CategoryResponseDTO> listagemCategoria = resultado.map(category -> categoryMapper.toDTO(category));
        return listagemCategoria;
    }
}
