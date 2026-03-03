package org.logonedigital.mboa_care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedecinReqDto {
    @NotEmpty(message = "Champ obligatoire")
    private String nom;
    @NotEmpty(message = "Champ obligatoire")
    @Email(message = "format de l'email est incorrect ")
    private String email;
    @NotEmpty(message = "Champ obligatoire")
    @Length(min = 9, max = 15,
            message = "doit imperativement avoir minimum 9 chiffres")
    private String telephone;
    @NotEmpty(message = "Champ obligatoire")
    private String specialite;
    private LocationDto location;
}
