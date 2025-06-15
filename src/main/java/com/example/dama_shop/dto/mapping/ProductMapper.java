package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.requests.ProductRequest;
import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest request);
    ProductResponse toResponse(Product product);
}
