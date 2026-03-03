package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DossierMedicaleDto {
    private String idPatient;
    private String nom;
    private String email;
    private String telephone;
    private String groupSanguin;
    private String antecedents;
    private LocalDate dateCreation;
}
