package com.pharmacie.mboacare.repository;

import com.pharmacie.mboacare.entity.Pharmaci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmaciRepo extends JpaRepository<Pharmaci, String> {
    Optional<Pharmaci> findByEmail(String Email);
}
