package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Patient extends Utilisateur {

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    public Patient(String nomUtilisateur, String email, String telephone, String password, Location location) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setPassword(password);
        this.setRole(Role.PATIENT);
        this.setCreatedAt(LocalDate.now());
        this.location = location;
    }
}