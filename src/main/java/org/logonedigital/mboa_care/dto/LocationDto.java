package org.logonedigital.mboa_care.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    @NotEmpty(message = "localisation obligatoire")
    private String ville;
    @NotEmpty(message = "localisation obligatoire")
    private String quartier;
}
