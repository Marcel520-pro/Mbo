package com.logonedigital.MBOAcare.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class StockReqdto {

    @NotNull(message = "veuillez remplir ce champ")

    private int quantite;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String nom;

    private List<MedicamentReqdto> medicaments;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<MedicamentReqdto> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentReqdto> medicaments) { this.medicaments = medicaments; }




    public StockReqdto(int quantite, String nom) {
        this.quantite = quantite;
        this.nom = nom;
    }

    public StockReqdto() {
    }
}
