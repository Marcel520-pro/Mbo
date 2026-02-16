package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
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
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, nullable = false, updatable = false)
    private String idUtilisateur;

    @Column(nullable = false)
    private String nomUtilisateur;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone;

    private LocalDate dateNaissance;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
