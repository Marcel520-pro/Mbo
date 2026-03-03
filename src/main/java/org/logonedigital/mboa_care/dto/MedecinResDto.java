package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedecinResDto {
    private String idUtilisateur;
    private String nom;
    private String email;
    private String telephone;
    private String specialite;
    private LocationDto locationDto;
}
