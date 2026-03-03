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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String idUtilisateur;
    private String nom;
    private String email;
    private String telephone;
    private LocalDate createdAt =  LocalDate.now()  ;

    @OneToOne
    private Location location;
}
