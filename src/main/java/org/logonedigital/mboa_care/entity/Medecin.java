package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Medecin extends Utilisateur {

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
    public Medecin(String nomUtilisateur, String email, String telephone, String password, Location location) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setPassword(password);
        this.setRole(Role.MEDECIN);
        this.setCreatedAt(LocalDate.now());
        this.location = location;
    }
}

