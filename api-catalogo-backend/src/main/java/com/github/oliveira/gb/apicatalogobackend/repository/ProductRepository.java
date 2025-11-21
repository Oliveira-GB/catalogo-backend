package com.github.oliveira.gb.apicatalogobackend.repository;

import com.github.oliveira.gb.apicatalogobackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
