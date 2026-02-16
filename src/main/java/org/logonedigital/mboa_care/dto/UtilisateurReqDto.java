package org.logonedigital.mboa_care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurReqDto {
    @NotEmpty(message = "Le nom est obligatoire")
    private String nomUtilisateur;
    @NotEmpty(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;
    @NotEmpty(message = "Le téléphone est obligatoire")
    private String telephone;
    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String motDePasse;
    // Date de naissance optionnelle
    private String dateNaissance;
    // Pour le patient uniquement
    private LocationReqDto location;
}
