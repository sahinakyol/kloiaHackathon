package com.example.kloia.service;


import com.example.kloia.model.Seller;
import com.example.kloia.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public Flux<Seller> findAll() {
        return sellerRepository.findAll();
    }


    public Mono<Seller> findById(String id) {
        return sellerRepository.findById(id);
    }

    public Mono<Seller> saveOrUpdateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
}
