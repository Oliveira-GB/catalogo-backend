package com.github.oliveira.gb.apicatalogobackend.mappers;

import com.github.oliveira.gb.apicatalogobackend.dto.CategoryRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toDTO(Category entity);
}
