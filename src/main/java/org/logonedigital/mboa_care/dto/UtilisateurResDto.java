package org.logonedigital.mboa_care.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurResDto {
    private String idUtilisateur;
    private String nomUtilisateur;
    private String email;
    private String telephone;
    private String role;
    private String ville;      // si patient
    private String quartier;   // si patient
}
