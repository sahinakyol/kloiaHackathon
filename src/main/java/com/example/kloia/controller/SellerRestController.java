package com.example.kloia.controller;

import com.example.kloia.model.Product;
import com.example.kloia.model.Seller;
import com.example.kloia.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/sellers")
@Slf4j
public class SellerRestController {

    @Autowired
    private SellerService sellerService;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl("http://localhost:8080/products")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Seller>  findAllSellers(){
        return sellerService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Seller>  findById(@PathVariable String id){
        return sellerService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Seller> saveSeller(@RequestBody Seller seller) {
        return sellerService.saveOrUpdateSeller(seller);
    }

    @GetMapping(value = "/{sellerId}/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Product> findAllProductsBySeller(@PathVariable String sellerId){

        return webClient.get().uri("/"+ sellerId + "/products").retrieve().bodyToFlux(Product.class);
    }



}
