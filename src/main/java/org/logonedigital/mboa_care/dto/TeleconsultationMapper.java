package org.logonedigital.mboa_care.dto;

import org.logonedigital.mboa_care.entity.CompteRendu;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.springframework.stereotype.Component;

@Component
public class TeleconsultationMapper {

    public Teleconsultation toEntity(TeleconsultationRequestDto dto) {
        return Teleconsultation.builder()
                .dateConsultation(dto.getDateConsultation())
                .motif(dto.getMotif())
                .emailDestinataire(dto.getEmailDestinataire())
                .build();
    }

    public TeleconsultationResponseDto toDto(Teleconsultation entity) {
        return TeleconsultationResponseDto.builder()
                .idConsultation(entity.getIdConsultation())
                .dateConsultation(entity.getDateConsultation())
                .dateCreation(entity.getDateCreation())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .statut(entity.getStatut().name())
                .motif(entity.getMotif())
                .emailDestinataire(entity.getEmailDestinataire())
                .build();
    }

    public CompteRenduResponseDto toDto(CompteRendu entity) {
        return CompteRenduResponseDto.builder()
                .id(entity.getId())
                .consultationId(entity.getConsultation().getIdConsultation())
                .contenu(entity.getContenu())
                .emailDestinataire(entity.getEmailDestinataire())
                .dateRendu(entity.getDateRendu().toString())
                .build();
    }
}