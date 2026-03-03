package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.springframework.data.domain.Page;

public interface MedecinService {
    void ajouterMedecin(MedecinReqDto medecinReqDto);
    void supprimerMedecin(String idUtilisateur);
    Page<MedecinResDto> listerMedecin(int page, int size);
    void modifierMedecin(String idUtilisateur, MedecinReqDto medecinReqDto);
    Page<MedecinResDto> rechercherParSpecialite(String specialite, int page, int size);
    Page<MedecinResDto> rechercheSpecialisteVille(String specialite, String ville, int page, int size);

}
