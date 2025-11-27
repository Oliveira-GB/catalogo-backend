package com.github.oliveira.gb.apicatalogobackend.repository.specs;

import com.github.oliveira.gb.apicatalogobackend.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecs {
    public static Specification<Product> nameLike(String name){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
    }

    public static Specification<Product> priceEqual(BigDecimal price){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }
}
