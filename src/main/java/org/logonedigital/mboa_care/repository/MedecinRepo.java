package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepo extends JpaRepository<Medecin, String> {
    Page<Medecin> findBySpecialite(String specialite, Pageable pageable);
    Page<Medecin> findBySpecialiteAndLocation_Ville(
            String specialite, String ville, Pageable pageable
    );
}
