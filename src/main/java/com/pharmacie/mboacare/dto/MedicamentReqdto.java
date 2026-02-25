package com.pharmacie.mboacare.dto;

import jakarta.validation.constraints.NotEmpty;

public class MedicamentReqdto {


    @NotEmpty(message = "veuillez remplir ce champ")

    private String nom;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String forme;

    public MedicamentReqdto() {
    }

    public MedicamentReqdto(String nom, String forme) {
        this.nom = nom;
        this.forme = forme;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }
}
