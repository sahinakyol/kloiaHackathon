package com.example.kloia.repository;

import com.example.kloia.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findBySellerId(Mono<String> sellerId);
}
