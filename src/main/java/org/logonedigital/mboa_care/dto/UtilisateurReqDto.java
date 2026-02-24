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
    @NotEmpty(message = "Veillez remplir ce champ")
    private String nomUtilisateur;
    @NotEmpty(message = "Veillez remplir ce champ")
    @Email(message = "format de l'email faux")
    private String email;
    @NotEmpty(message = "Veillez remplir ce champ")
    private String telephone;
    private String motDePasse;
    private String role;
    private LocationReqDto location;

}
