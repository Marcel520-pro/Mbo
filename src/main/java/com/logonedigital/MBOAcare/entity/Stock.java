package com.logonedigital.MBOAcare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



    @Entity
    @Table(name = "STOCK")

    public class Stock {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String idStock;
        private int quantite;
        private String nom;

        @OneToMany (mappedBy = "stock" )
        @JsonManagedReference
        private List<Medicament> medicaments = new ArrayList<>();

        @ManyToOne
        @JsonBackReference
        private Pharmaci pharmaci;

        public Stock(int quantite, String nom) {
            this.quantite = quantite;
            this.nom = nom;
            this.medicaments = medicaments;
        }

    public Stock(String idStock, int quantite, String nom) {
        this.idStock = idStock;
        this.quantite = quantite;
        this.nom = nom;
    }

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

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

        public Stock() {
        }

    }
