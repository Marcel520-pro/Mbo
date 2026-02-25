package com.pharmacie.mboacare.dto;

import java.time.LocalDate;
import java.util.Date;

public class PharmaciResdto {
    private String idPharmaci;
    private String nom ;
    private LocalDate dateCreation ;
    private String ville ;
    private String quartier ;
    private String email ;

    public PharmaciResdto() {
    }

    public PharmaciResdto(String idPharmaci, String nom,LocalDate dateCreation, String ville, String quartier, String email) {
        this.idPharmaci = idPharmaci;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.ville = ville;
        this.quartier = quartier;
        this.email = email;
    }

    public String getIdPharmaci() {
        return idPharmaci;
    }

    public void setIdPharmaci(String idPharmaci) {
        this.idPharmaci = idPharmaci;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
