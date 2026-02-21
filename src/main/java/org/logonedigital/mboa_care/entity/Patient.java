package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Patient extends Utilisateur{
    public Patient(String idUtilisateur, String nomUtilisateur, String email,
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
}
