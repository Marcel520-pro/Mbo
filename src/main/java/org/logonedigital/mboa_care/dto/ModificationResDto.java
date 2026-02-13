package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModificationResDto {
    private String nomUtilisateur;
    private String email;
    private String telephone;
    private String ville;
    private String quartier;
    private LocationReqDto location;

}
