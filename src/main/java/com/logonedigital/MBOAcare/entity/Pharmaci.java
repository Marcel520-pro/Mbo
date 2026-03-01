package com.logonedigital.MBOAcare.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "PHARMACIE")


public class Pharmaci {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPharmaci;


    private String nom;
    private LocalDate dateCreation;
    private String ville;
    private String quartier;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "pharmaci", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Stock> stocks = new ArrayList<>();


    public Pharmaci() {
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

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

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Pharmaci(String nom, String ville, String quartier, String email, List<Stock> stocks) {
        this.nom = nom;
        this.ville = ville;
        this.quartier = quartier;
        this.email = email;
        this.dateCreation = LocalDate.now();
        this.stocks = stocks != null ? stocks : new ArrayList<>();
        for (Stock s : this.stocks) {
            s.setPharmaci(this);
        }
    }


    }

