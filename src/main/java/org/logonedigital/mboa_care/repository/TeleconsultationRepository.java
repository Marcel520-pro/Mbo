package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.StatutConsultation;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeleconsultationRepository
        extends JpaRepository<Teleconsultation, String> {

    Page<Teleconsultation> findByStatutOrderByDateCreationAsc(
            StatutConsultation statut, Pageable pageable
    );

    Optional<Teleconsultation> findFirstByStatutOrderByDateCreationAsc(
            StatutConsultation statut
    );
}