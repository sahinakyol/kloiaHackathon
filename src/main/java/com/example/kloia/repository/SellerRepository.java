package com.example.kloia.repository;

import com.example.kloia.model.Seller;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends ReactiveMongoRepository<Seller, String> {
}
