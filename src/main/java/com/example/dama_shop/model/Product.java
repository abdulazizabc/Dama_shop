package com.example.dama_shop.model;

import com.example.dama_shop.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    private String productDescription;

    @Column(nullable = false)
    private double productPrice;

    @Column(nullable = false)
    private int productQuantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

}
