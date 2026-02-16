package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    // Informations de localisation (uniquement pour les patients)
    private String ville;
    private String quartier;
    // Optionnel : âge calculé à partir de la date de naissance
    private Integer age;
}
