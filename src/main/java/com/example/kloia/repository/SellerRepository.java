package com.example.kloia.repository;

import com.example.kloia.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository   extends JpaRepository<Seller, Long>{
}
