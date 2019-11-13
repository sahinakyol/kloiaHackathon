package com.example.kloia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class KloiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KloiaApplication.class, args);
    }

}
