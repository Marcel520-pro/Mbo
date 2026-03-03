package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    void ajouterPatient(PatientReqDto patientReqDto);
    PatientResDto consulterPatient(String idUtilisateur);
    Page<PatientResDto> getAllPatients(Pageable pageable);
    void supprimerPatient(String idUtilisateur);
    void modifierPatient(PatientReqDto patientReqDto);
}
