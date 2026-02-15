package org.logonedigital.mboa_care.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ConsultationResDto {
    private String idConsultation;
    private LocalDateTime dateConsultation;
    private String motif;
    private String statut;
    private String nomPatient;
    private String nomMedecin;
}
