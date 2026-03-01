package com.logonedigital.MBOAcare.dto;

public class MedicamentResdto {
    private String idMedicament;
    private String nom;
    private String forme;

    public String getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(String idMedicament) {
        this.idMedicament = idMedicament;
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

    public MedicamentResdto(String idMedicament, String nom, String forme) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.forme = forme;
    }

    public MedicamentResdto() {
    }
}
