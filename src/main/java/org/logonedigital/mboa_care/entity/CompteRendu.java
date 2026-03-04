package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
import lombok.*;
import org.logonedigital.mboa_care.entity.Teleconsultation;

import java.time.LocalDateTime;

@Entity
@Table(name = "compte_rendu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteRendu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false, unique = true)
    private Teleconsultation consultation;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private LocalDateTime dateRendu;

    private String emailDestinataire;
}