package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;

import java.util.List;

public interface ProfilService {

     void ajouterPatient(UtilisateurReqDto utilisateurReqDto);
     void ajouterMedecin(UtilisateurReqDto utilisateurReqDto);
     UtilisateurResDto consulterProfil(String idUtilisateur);
     List<UtilisateurResDto> listerProfil();
     void modifierProfil(String idUtilisateur,UtilisateurReqDto utilisateurReqDto);
     void supprimerProfil(String idUtilisateur);
     List<UtilisateurResDto> getAllPatients();
     List<UtilisateurResDto> getAllMedecins();
}
