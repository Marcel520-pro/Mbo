package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.logonedigital.mboa_care.entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResDto {
    private String idUtilisateur;
    private String nomUtilisateur;
    private String email;
    private String telephone;
    private Role role;
    private LocationDto location;
}
