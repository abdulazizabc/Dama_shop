package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.mapping.ProductMapper;
import com.example.dama_shop.dto.requests.ProductRequest;
import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.enums.ProductCategory;
import com.example.dama_shop.repository.ProductRepository;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id).orElse(null);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " does not exist");
        }
        Product product = productMapper.toEntity(request);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }


    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

}
