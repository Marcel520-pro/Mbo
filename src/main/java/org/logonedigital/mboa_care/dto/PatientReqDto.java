package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.logonedigital.mboa_care.entity.Location;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientReqDto {
    private String nom;
    private String email;
    private String password;
    private String telephone;
    private String allergies;
    private String groupSanguin;
    private String antecedents;
    private LocationDto locationDto;
}
