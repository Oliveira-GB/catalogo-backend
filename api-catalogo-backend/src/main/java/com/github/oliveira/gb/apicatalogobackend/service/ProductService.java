package com.github.oliveira.gb.apicatalogobackend.service;

import com.github.oliveira.gb.apicatalogobackend.model.Product;
import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product salvar(Product product){
        // DTO aQUI
        //Conversao por Map
        //Pegar id das categOrias, do set
        // COm id das categorias ai sim fazer um find com os dados
        //Preenche
        return productRepository.save(product);
    }
}
