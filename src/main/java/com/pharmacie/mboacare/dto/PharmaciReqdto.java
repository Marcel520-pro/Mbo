package com.pharmacie.mboacare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class PharmaciReqdto {


    @NotEmpty(message = "veuillez remplir ce champ")

    private String nom;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String email;

    @NotEmpty(message = "veuillez remplir ce champ")
    @Email(message = "cette email est erone")

    private String ville ;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String quartier ;

    public PharmaciReqdto() {
    }

    public PharmaciReqdto(String nom, String email, String ville, String quartier) {
        this.nom = nom;
        this.email = email;
        this.ville = ville;
        this.quartier = quartier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }
}

