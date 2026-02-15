package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepo extends JpaRepository<Utilisateur, String> {
    boolean existsByEmail(String email);
}
