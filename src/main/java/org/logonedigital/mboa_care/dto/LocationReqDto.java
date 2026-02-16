package org.logonedigital.mboa_care.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationReqDto {
    @NotEmpty(message = "La ville est obligatoire")
    private String ville;
    @NotEmpty(message = "Le quartier est obligatoire")
    private String quartier;
}