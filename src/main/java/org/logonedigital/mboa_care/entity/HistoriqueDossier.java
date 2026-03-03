package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriqueDossier {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String idDossier;
    private String idPatient;
    private String nom;
    private String email;
    private String telephone;
    private String groupSanguin;
    private String antecedents;
    private LocalDate dateTelechargement;

}