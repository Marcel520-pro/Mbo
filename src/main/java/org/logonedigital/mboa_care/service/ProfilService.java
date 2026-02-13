package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;

import java.util.List;

public interface ProfilService {
    UtilisateurReqDto creerProfil(UtilisateurReqDto utilisateurReqDto);
    void modifierProfil(String idUtilisateur, UtilisateurReqDto utilisateurReqDto);
    UtilisateurResDto consulterProfil(String idUtilisateur);
    List<UtilisateurResDto> listerProfil();
    void supprimerProfil(String idUtilisateur);
}
