package org.logonedigital.mboa_care.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeleconsultationResponseDto {
    private String idConsultation;
    private LocalDateTime dateConsultation;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private String motif;
    private String emailDestinataire;
}