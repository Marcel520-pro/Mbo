package org.logonedigital.mboa_care.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_utilisateur")
    private String idUtilisateur;
    private String nomUtilisateur;
    private String email;
    private String telephone;
    private String password;

//    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate createdAt;
    private LocalDate updatedAt;

}
