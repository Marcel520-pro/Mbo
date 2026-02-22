package org.logonedigital.mboa_care.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedecinReqDto {
    @NotEmpty(message = "nom obligatoire ")
    private String nomUtilisateur;
    @Email(message = "le format de l'email est incorrect")
    @NotEmpty(message = "email obligatoire")
    private String email;
    @NotEmpty(message = "numero de telephone obligatoire")
    private String telephone;
    @NotEmpty(message = "mot de passe obligatoire")
    private String password;
    @Valid
    @NotEmpty(message = "localisation obligatoire")
    private LocationDto location;
    @NotEmpty(message = "specialite obligatoire")
    private String specialite;
}
