package com.logonedigital.MBOAcare.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MedicamentReqdto {

    @NotNull(message = "veuillez remplir ce champ")

    private String nom;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String forme;

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public MedicamentReqdto(String nom, String forme) {
        this.nom = nom;
        this.forme = forme;
    }

    public MedicamentReqdto() {
    }
}
