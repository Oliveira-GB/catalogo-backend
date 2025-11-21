package com.github.oliveira.gb.apicatalogobackend.repository;

import com.github.oliveira.gb.apicatalogobackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
