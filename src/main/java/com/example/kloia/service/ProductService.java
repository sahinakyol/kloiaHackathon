package com.example.kloia.service;

import com.example.kloia.model.Product;
import com.example.kloia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }


    public Flux<Product> findAllProductsBySellerId(Mono<String> sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }
}
