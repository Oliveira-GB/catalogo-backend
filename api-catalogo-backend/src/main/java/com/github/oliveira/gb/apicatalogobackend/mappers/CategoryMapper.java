package com.github.oliveira.gb.apicatalogobackend.mappers;

import com.github.oliveira.gb.apicatalogobackend.dto.CategoryRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryUpdateDTO;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toDTO(Category entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(CategoryUpdateDTO dto, @MappingTarget Category categoryEntity);
}
