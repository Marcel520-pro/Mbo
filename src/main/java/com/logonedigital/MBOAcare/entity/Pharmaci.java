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

    @OneToMany(mappedBy = "pharmaci")
    @JsonManagedReference
    private List<Stock> stocks = new ArrayList<>();


    public Pharmaci() {
    }

    public Pharmaci(String nom, String ville, String quartier, String email) {
        this.nom = nom;
        this.ville = ville;
        this.quartier = quartier;
        this.email = email;



    }
}
