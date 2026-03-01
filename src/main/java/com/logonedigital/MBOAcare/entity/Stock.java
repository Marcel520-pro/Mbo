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

        @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
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

        public Stock(String idStock, int quantite, String nom, Pharmaci pharmaci) {
            this.idStock = idStock;
            this.quantite = quantite;
            this.nom = nom;
            this.pharmaci = pharmaci;
        }
        public Stock(String nom, int quantite, List<Medicament> medicaments) {
            this.nom = nom;
            this.quantite = quantite;
            this.medicaments = medicaments != null ? medicaments : new ArrayList<>();
            for (Medicament m : this.medicaments) {
                m.setStock(this);
                m.setPharmaci(this.pharmaci);
            }
        }

        public Stock(String nom, String forme) {
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

        public Pharmaci getPharmaci() {
            return pharmaci;
        }

        public void setPharmaci(Pharmaci pharmaci) {
            this.pharmaci = pharmaci;
        }

        public List<Medicament> getMedicaments() {
            return medicaments;
        }

        public void setMedicaments(List<Medicament> medicaments) {
            this.medicaments = medicaments;
        }

        public Stock() {
        }

    }
