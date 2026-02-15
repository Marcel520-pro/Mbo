package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.ConsultationReqDto;
import org.logonedigital.mboa_care.dto.ConsultationResDto;
import org.logonedigital.mboa_care.entity.Teleconsultation;

import java.util.List;

public interface ConsultationService {

    void creerTeleconsultation(ConsultationReqDto consultationReqDto);

    List<Teleconsultation> listerTeleconsultations();

    ConsultationResDto getById(String idConsultation);

    void commencerConsultation(String idConsultation, String idMedecin);

    void terminerConsultation(String idConsultation, String idMedecin);

    void supprimerTeleconsultation(String idConsultation);
}

