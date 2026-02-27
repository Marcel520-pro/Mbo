package com.pharmacie.mboacare.entity;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "PHARMACIE")


public class Pharmaci {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPharmaci;

    @Column (length = 9 , name = "tel" , nullable = false , unique = true)

    private String contact ;

    private String nom ;
    private LocalDate dateCreation ;
    private String ville ;
    private String quartier ;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email ;

    @OneToMany(mappedBy = "pharmaci")
    private List<Stock> stocks = new ArrayList<>();

    public Pharmaci(String nom, String ville, String contact, String quartier, String email) {
        this.nom = nom;
        this.ville = ville;
        this.contact = contact;
        this.quartier = quartier;
        this.email = email;
    }
}

