package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Medecin extends Utilisateur {
    private String specialite;

    public Medecin(String nomUtilisateur, String email, String telephone, String password, Location location, Role role, LocalDate createdAt, LocalDate updatedAt, String specialite) {
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

    public Medecin(String nomUtilisateur,
                   String email,
                   String telephone,
                   String password,
                   String specialite, Location location) {

        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setPassword(password);
        this.setLocation(location);
        this.setRole(Role.MEDECIN);
        this.specialite = specialite;
    }
}