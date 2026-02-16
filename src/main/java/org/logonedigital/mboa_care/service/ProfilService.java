package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.ModificationResDto;
import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;

import java.util.List;

public interface ProfilService {
    void ajouterPatient(UtilisateurReqDto dto);
    void ajouterMedecin(UtilisateurReqDto dto);
    void modifierProfil(String idUtilisateur, UtilisateurReqDto dto);
    UtilisateurResDto consulterProfil(String idUtilisateur);
    List<UtilisateurResDto> listerProfil();
    void supprimerProfil(String idUtilisateur);
    List<UtilisateurResDto> getAllPatients();
    List<UtilisateurResDto> getAllMedecins();

}
