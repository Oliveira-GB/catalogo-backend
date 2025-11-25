package com.github.oliveira.gb.apicatalogobackend.service;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.exception.CategoriaNaoEncontradaException;
import com.github.oliveira.gb.apicatalogobackend.mappers.ProductMapper;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
import com.github.oliveira.gb.apicatalogobackend.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductValidator productValidator;

    @Transactional
    public ProductResponseDTO salvar(ProductRequestDTO productRequestDTO){
        this.productValidator.validar(productRequestDTO);
        var productEntity = productMapper.toEntity(productRequestDTO);

        if (productRequestDTO.categoryIds() != null && !productRequestDTO.categoryIds().isEmpty()){
            List<Category> categories = categoryRepository.findAllById(productRequestDTO.categoryIds());

            if (productRequestDTO.categoryIds().size() != categories.size()){
                throw new CategoriaNaoEncontradaException("Categoria não encontrada");
            }
            productEntity.setCategories(new HashSet<>(categories));
        }
        productEntity = productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }
}
