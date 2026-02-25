package com.pharmacie.mboacare.repository;

import com.pharmacie.mboacare.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepo  extends JpaRepository<Stock, String> {
    Optional<Stock> findByNom(String nom);

}
