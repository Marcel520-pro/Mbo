package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfilService {
    void ajouterPatient(PatientReqDto patientReqDto);
    void ajouterMedecin(MedecinReqDto medecinReqDto);
    PatientResDto consulterPatient(String idUtilisateur);
    MedecinResDto consulterMedecin(String idUtilisateur);
    Page<MedecinResDto> listerMedecin(int page, int size);
    Page<PatientResDto> listerPatients(int page, int size);
    void modifierPatient(String idUtilisateur, PatientReqDto patientReqDto);
    void modifierMedecin(String idUtilisateur, MedecinReqDto medecinReqDto);
    void supprimerProfil(String idUtilisateur);
}
