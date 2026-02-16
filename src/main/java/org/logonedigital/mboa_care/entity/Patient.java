package org.logonedigital.mboa_care.entity;

import java.time.LocalDate;

public class Patient extends Utilisateur{
    public Patient(String nomUtilisateur, String email, String password,
                   String telephone, Location location) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setPassword(password);
        this.setTelephone(telephone);
        this.setRole(Role.PATIENT);  // attribution automatique
        this.setLocation(location);
        this.setCreatedAt(LocalDate.now());
    }
}
