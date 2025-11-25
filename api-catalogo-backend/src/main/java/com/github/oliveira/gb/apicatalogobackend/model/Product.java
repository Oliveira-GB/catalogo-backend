package com.github.oliveira.gb.apicatalogobackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name",  nullable = false, length = 150)
    @NotBlank(message = "Nome não pode estar em branco")
    @Size(min = 5, max = 150, message = "Nome completo não deve ultrapassar 250 caracteres")
    private String name;

    @Column(name = "description",  nullable = false, length = 500, columnDefinition = "TEXT")
    @NotBlank(message = "É obrigatório ter uma descrição do produto!!")
    @Size(min = 10, max = 500, message = "Descrição não pode ultrapassar 500 caracteres.")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @Positive(message = "O preço deve ser maior que 0, não é permitido itens gratuiros")
    @Digits(integer = 4, fraction = 2, message = "Valor alto demais ninguem é rico aqui não filho!")
    private BigDecimal price;

    @Column(name = "quantity")
    @PositiveOrZero(message = "Não é permitido quantidades negativas")
    private Integer quantity;

    @ManyToMany
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
