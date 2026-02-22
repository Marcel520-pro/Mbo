package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_utilisateur")
public class Patient extends Utilisateur{
    public Patient(String nomUtilisateur, String email,
                   String telephone, String password, Location location,
                   Role role, LocalDate createdAt, LocalDate updatedAt) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setPassword(password);
        this.setLocation(location);
        this.setRole(Role.PATIENT);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public Patient(String nomUtilisateur,
                   String telephone,
                   String email,
                   String password,
                   Location location) {

        this.setNomUtilisateur(nomUtilisateur);
        this.setTelephone(telephone);
        this.setEmail(email);
        this.setPassword(password);
        this.setLocation(location);
        this.setRole(Role.PATIENT);
    }
}
