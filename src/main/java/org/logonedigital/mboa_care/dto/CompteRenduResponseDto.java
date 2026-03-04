package org.logonedigital.mboa_care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteRenduResponseDto {
    private String id;
    private String consultationId;
    private String contenu;
    private String emailDestinataire;
    private String dateRendu;
}
