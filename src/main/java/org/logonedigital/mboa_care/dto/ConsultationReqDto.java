package org.logonedigital.mboa_care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationReqDto {
    private LocalDateTime dateConsultation;
    @NotEmpty(message = "Veuillez remplir ce champ")
    private String motif;
    @NotEmpty(message = "Veuillez remplir ce champ")
    private String idPatient;
    @NotEmpty(message = "Veuillez remplir ce champ")
    private String idMedecin;
}
