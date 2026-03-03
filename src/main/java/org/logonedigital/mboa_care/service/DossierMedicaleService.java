package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.DossierMedicaleDto;
import org.springframework.data.domain.Page;

public interface DossierMedicaleService {
    byte[] genererDossierMedicale(String idUtilisateur);
    Page<DossierMedicaleDto> historiqueTelechargements(int page, int size);
    byte[] genererQrCode(String idUtilisateur);
}
