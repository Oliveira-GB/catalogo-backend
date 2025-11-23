package com.github.oliveira.gb.apicatalogobackend.service;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.execption.CategoriaNaoEncontradaException;
import com.github.oliveira.gb.apicatalogobackend.mappers.ProductMapper;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.model.Product;
import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO salvar(ProductRequestDTO productRequestDTO){
        var productEntity = productMapper.toEntity(productRequestDTO);

        if (productRequestDTO.categoryIds() != null && !productRequestDTO.categoryIds().isEmpty()){
            List<Category> categorys = categoryRepository.findAllById(productRequestDTO.categoryIds());

            if (productRequestDTO.categoryIds().size() != productEntity.getCategories().size() ){
                throw new CategoriaNaoEncontradaException("Categoria nã encontrada");
            }
            productEntity.setCategories(new HashSet<>(categorys));
        }
        productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }
}
