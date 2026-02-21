package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Medecin extends Utilisateur{
    private String specialite;

    public Medecin(String idUtilisateur, String nomUtilisateur, String email, String telephone, String password, Location location, Role role, LocalDate createdAt, LocalDate updatedAt, String specialite) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setPassword(password);
        this.setLocation(location);
        this.setRole(Role.MEDECIN);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.specialite = specialite;
    }

}
