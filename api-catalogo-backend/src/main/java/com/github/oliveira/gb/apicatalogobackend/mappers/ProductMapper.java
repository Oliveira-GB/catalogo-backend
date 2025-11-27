package com.github.oliveira.gb.apicatalogobackend.mappers;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.ProductUpdateDTO;
import com.github.oliveira.gb.apicatalogobackend.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toDTO(Product entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProductUpdateDTO dto, @MappingTarget Product entity);
}
