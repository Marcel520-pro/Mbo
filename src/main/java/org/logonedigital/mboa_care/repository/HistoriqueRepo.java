package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.dto.DossierMedicaleDto;
import org.logonedigital.mboa_care.entity.HistoriqueDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepo extends JpaRepository<HistoriqueDossier, String> {
}
