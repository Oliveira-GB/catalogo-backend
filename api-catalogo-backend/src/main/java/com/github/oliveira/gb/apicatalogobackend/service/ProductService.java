    package com.github.oliveira.gb.apicatalogobackend.service;

    import com.github.oliveira.gb.apicatalogobackend.dto.ProductRequestDTO;
    import com.github.oliveira.gb.apicatalogobackend.dto.ProductResponseDTO;
    import com.github.oliveira.gb.apicatalogobackend.dto.ProductUpdateDTO;
    import com.github.oliveira.gb.apicatalogobackend.exception.CategoriaNaoEncontradaException;
    import com.github.oliveira.gb.apicatalogobackend.exception.RecursoNaoEncontradoException;
    import com.github.oliveira.gb.apicatalogobackend.mappers.ProductMapper;
    import com.github.oliveira.gb.apicatalogobackend.model.Category;
    import com.github.oliveira.gb.apicatalogobackend.model.Product;
    import com.github.oliveira.gb.apicatalogobackend.repository.CategoryRepository;
    import com.github.oliveira.gb.apicatalogobackend.repository.ProductRepository;
    import com.github.oliveira.gb.apicatalogobackend.repository.specs.ProductSpecs;
    import com.github.oliveira.gb.apicatalogobackend.validator.ProductValidator;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.domain.Specification;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;

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

        @Transactional(readOnly = true)
        public ProductResponseDTO obterPorId(UUID id){
            Optional<Product> productOpt = productRepository.findById(id);

            if (productOpt.isEmpty()){
                throw new RecursoNaoEncontradoException("Não foi encontrado o Produto");
            }

            var productEncontrado = productOpt.get();
            return productMapper.toDTO(productEncontrado);
        }

        @Transactional
        public void deletarPorId(UUID id){
            var productEncontrado = productRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Não possui o Produto!!!"));

            productRepository.delete(productEncontrado);
        }

        @Transactional(readOnly = true)
        public Page<ProductResponseDTO> pesquisar(String nome, BigDecimal price, Pageable pageable){

            Specification<Product> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

            if (nome != null){
                specs = specs.and(ProductSpecs.nameLike(nome));
            }

            if (price != null){
                specs = specs.and(ProductSpecs.priceEqual(price));
            }

            return productRepository.findAll(specs, pageable)
                    .map(productMapper::toDTO);
        }

        @Transactional
        public ProductResponseDTO atualizar(UUID id, ProductUpdateDTO dto){
            Product productEntity = productRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
            productValidator.validar(dto, id);

            productMapper.updateEntityFromDto(dto, productEntity);
            productRepository.save(productEntity);
            return productMapper.toDTO(productEntity);
        }
    }
