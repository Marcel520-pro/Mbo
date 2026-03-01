package org.logonedigital.mboa_care.repository;


import org.logonedigital.mboa_care.entity.Medecin;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilRepo extends JpaRepository<Utilisateur, String> {
    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmail(String email);
    Page<Utilisateur> findByRole(Role role, Pageable pageable);
    Page<Medecin> findBySpecialite(String specialite, Pageable pageable);
    Page<Medecin> findBySpecialiteAndLocation_Ville(String specialite, String ville, Pageable pageable);
}