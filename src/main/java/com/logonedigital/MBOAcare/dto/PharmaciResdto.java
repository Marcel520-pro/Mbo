package com.logonedigital.MBOAcare.dto;


public class PharmaciResdto {

        private String idPharmaci;
        private String nom;
        private String ville;
        private String quartier;
        private String email;

    public String getIdPharmaci() {
        return idPharmaci;
    }

    public void setIdPharmaci(String idPharmaci) {
        this.idPharmaci = idPharmaci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public PharmaciResdto(String idPharmaci, String nom, String email, String ville, String quartier) {
        this.idPharmaci = idPharmaci;
        this.nom = nom;
        this.email = email;
        this.ville = ville;
        this.quartier = quartier;


    }

    public PharmaciResdto() {
    }
}

