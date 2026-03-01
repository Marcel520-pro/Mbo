package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.DossierHistory;
import org.logonedigital.mboa_care.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierHistoryRepo extends JpaRepository<DossierHistory, String> {
    List<DossierHistory> findByidDossier(String idDossier);
    Page<DossierHistory> findByPatient(Patient patient, Pageable pageable);
}
