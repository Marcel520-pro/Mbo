package org.logonedigital.mboa_care.entity;

import java.time.LocalDate;

public class Medecin extends Utilisateur{
    public Medecin(String nomUtilisateur, String email, String password, String telephone) {
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setPassword(password);
        this.setTelephone(telephone);
        this.setRole(Role.MEDECIN); // attribution automatique
        this.setCreatedAt(LocalDate.now());
    }
}
