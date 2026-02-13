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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String idUtilisateur;
    private String nomUtilisateur;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    private String telephone;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
