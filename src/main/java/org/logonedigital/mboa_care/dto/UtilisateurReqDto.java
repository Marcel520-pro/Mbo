package org.logonedigital.mboa_care.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurReqDto {
    @NotEmpty(message = "Nom obligatoire")
    private String nomUtilisateur;

    @Email(message = "format de l'email incorrect")
    @NotEmpty(message = "Email obligatoire")
    private String email;

    @NotEmpty(message = "Téléphone obligatoire")
    private String telephone;

    @NotEmpty(message = "Mot de passe obligatoire")
    private String motDePasse;

    private LocationReqDto location;
}
