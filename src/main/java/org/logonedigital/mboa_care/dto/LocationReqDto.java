package org.logonedigital.mboa_care.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationReqDto {
    private String ville;
    private String quartier;
}