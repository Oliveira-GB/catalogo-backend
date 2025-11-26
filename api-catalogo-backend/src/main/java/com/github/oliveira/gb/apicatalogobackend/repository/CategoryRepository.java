package com.github.oliveira.gb.apicatalogobackend.repository;

import com.github.oliveira.gb.apicatalogobackend.model.Category;
import com.github.oliveira.gb.apicatalogobackend.model.Product;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByNameIgnoreCase(String name);
}
