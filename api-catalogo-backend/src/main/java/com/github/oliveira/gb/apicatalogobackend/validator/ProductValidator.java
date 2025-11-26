package com.github.oliveira.gb.apicatalogobackend.validator;

import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.exception.RegistroDuplicadoException;
import com.github.oliveira.gb.apicatalogobackend.model.Product;
import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductRepository productRepository;

    public void validar(ProductRequestDTO dto){
        if (checkNomeDuplicado(dto.name(), null)){
            throw new RegistroDuplicadoException("Já existe um produto com este nome!!");
        }
    }

    public void validar(ProductRequestDTO dto, UUID idProdutoAtualizado){
        if (checkNomeDuplicado(dto.name(), idProdutoAtualizado)){
            throw new RegistroDuplicadoException("Já existe um produto com este nome!!");
        }
    }

    private boolean checkNomeDuplicado(String name, UUID idParaIgnorar){
        Optional<Product> productEncontrado = productRepository.findByNameIgnoreCase(name);

        if (productEncontrado.isEmpty()){
            return false;
        }

        Product product = productEncontrado.get();

        if (idParaIgnorar == null){
            return true;
        }

        return !product.getId().equals(idParaIgnorar);
    }
}
