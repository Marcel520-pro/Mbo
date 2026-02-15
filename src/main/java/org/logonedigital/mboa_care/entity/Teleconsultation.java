package org.logonedigital.mboa_care.entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultations")
public class Teleconsultation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String idConsultation;
    private LocalDateTime consultationDate;
    @Column(length = 100, nullable = false)
    private String motif;
    @Enumerated(EnumType.STRING)
    private Statut status;

    @ManyToOne
    @JoinColumn(name = "idPatient", nullable = false)
    private Utilisateur patient;

    @ManyToOne
    @JoinColumn(name = "idMedecin", nullable = false)
    private Utilisateur medecin;
}
