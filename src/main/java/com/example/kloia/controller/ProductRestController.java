package com.example.kloia.controller;

import com.example.kloia.model.Product;
import com.example.kloia.model.Seller;
import com.example.kloia.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductRestController {

    @Autowired
    private ProductService productService;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl("http://localhost:8080/sellers")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping(value = "/{sellerId}/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Product> findAllProductsBySellerId(@PathVariable String sellerId) {
        return productService.findAllProductsBySellerId(Mono.just(sellerId));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Product> saveProduct(@RequestBody Product product) {
        String sellerId = product.getSellerId();
        Mono<Seller> monoSeller = webClient.get().uri("/" + sellerId).retrieve().bodyToMono(Seller.class);

        Mono<Mono<Product>> map = monoSeller
                .map(seller -> {
                    product.setSellerId(seller.getId());
                    return productService.saveOrUpdateProduct(product);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Seller couldn't find!!")));

        return map.flatMap(Mono::single);
    }

}
