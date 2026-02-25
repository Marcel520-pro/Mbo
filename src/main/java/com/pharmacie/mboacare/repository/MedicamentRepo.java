package com.pharmacie.mboacare.repository;

import com.pharmacie.mboacare.entity.Medicament;
import com.pharmacie.mboacare.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentRepo  extends JpaRepository<Medicament, String> {
    Optional<Stock> findByNom(String nom);
}
