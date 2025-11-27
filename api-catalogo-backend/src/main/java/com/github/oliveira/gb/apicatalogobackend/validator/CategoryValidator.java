package com.github.oliveira.gb.apicatalogobackend.validator;

import com.github.oliveira.gb.apicatalogobackend.dto.CategoryRequestDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryResponseDTO;
import com.github.oliveira.gb.apicatalogobackend.dto.CategoryUpdateDTO;
import com.github.oliveira.gb.apicatalogobackend.exception.RegistroDuplicadoException;
import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.model.Product;
import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void validar(CategoryRequestDTO dto){
        if (checkNomeDuplicado(dto.name(), null)){
            throw new RegistroDuplicadoException("Nome de Categoria já usado!!");
        }
    }

    public void validar(CategoryRequestDTO dto, UUID id){
        if (checkNomeDuplicado(dto.name(), id)){
            throw new RegistroDuplicadoException("Nome de Categoria já usado!!");
        }
    }

    public void validar(CategoryUpdateDTO dto, UUID id){
        if (dto.name() != null){
            if (checkNomeDuplicado(dto.name(), id)){
                throw new RegistroDuplicadoException("Nome já está sendo usado!!");
            }
        }
    }

    public boolean checkNomeDuplicado(String name, UUID idParaIgnorar){
        Optional<Category> categoryEncontrada = categoryRepository.findByNameIgnoreCase(name);

        if (categoryEncontrada.isEmpty()){
            return false;
        }

        Category category = categoryEncontrada.get();

        if (idParaIgnorar == null){
            return true;
        }

        return !category.getId().equals(idParaIgnorar);
    }
}
