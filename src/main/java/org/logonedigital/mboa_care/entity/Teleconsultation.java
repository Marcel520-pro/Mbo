package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
import lombok.*;
import org.logonedigital.mboa_care.entity.StatutConsultation;

import java.time.LocalDateTime;

@Entity
@Table(name = "teleconsultation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teleconsultation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idConsultation;

    private LocalDateTime dateConsultation;

    private LocalDateTime dateCreation;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    private StatutConsultation statut;

    @Column(columnDefinition = "TEXT")
    private String motif;

    private String emailDestinataire;
}