package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.CompteRendu;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRenduRepository
        extends JpaRepository<CompteRendu, String> {

    Optional<CompteRendu> findByConsultation(Teleconsultation consultation);
}