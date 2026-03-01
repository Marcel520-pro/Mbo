package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDossier;
    private String action;
    private LocalDateTime dateAction;
    private String username;

    @ManyToOne
    private Patient patient;
}
