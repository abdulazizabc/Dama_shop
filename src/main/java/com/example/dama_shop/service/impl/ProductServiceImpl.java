package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.mapping.ProductMapper;
import com.example.dama_shop.dto.requests.ProductRequest;
import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.enums.ProductCategory;
import com.example.dama_shop.repository.ProductRepository;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("Get product by id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ForbiddenException("Product not found"));
        log.info("Product found: {}", product);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(ProductCategory category) {
        log.info("Get products by category: {}", category);
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
        log.info("Update product: {}", request);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        Product product = productMapper.toEntity(request);
        product.setId(id);
        log.info("save updated product: {}", product);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }


    @Override
    public void deleteProduct(Long id) {
        log.info("Delete product: {}", id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

}
