package com.logonedigital.MBOAcare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "MEDICAMENT")


public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String idMedicament;
    private String nom;
    private String forme;


    @ManyToOne
    @JsonBackReference
    private Stock stock;

    @ManyToOne
    @JsonBackReference
    private Pharmaci pharmaci;

    public Medicament() {
    }

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

    public Medicament( String nom , String forme) {
        this.nom = nom;
        this.forme = forme;

    }
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Pharmaci getPharmaci() {
        return pharmaci;
    }

    public void setPharmaci(Pharmaci pharmaci) {
        this.pharmaci = pharmaci;
    }

}
