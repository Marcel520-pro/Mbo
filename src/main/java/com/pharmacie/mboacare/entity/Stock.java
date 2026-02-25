package com.pharmacie.mboacare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Getter
@Setter
@AllArgsConstructor


@Entity
@Table(name = "STOCK")

public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idStock;
    private int quantite;
    private String nom;




    @OneToMany (mappedBy = "stock" )
    private List<Medicament> medicaments = new ArrayList<>();

    @ManyToOne
    private Pharmaci pharmaci;

    public Stock(int quantite, String nom) {
        this.quantite = quantite;
        this.nom = nom;
        this.medicaments = medicaments;
    }
}