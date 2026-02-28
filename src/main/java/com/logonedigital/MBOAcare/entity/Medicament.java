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
    @JsonIgnore
    @JoinColumn(name = "pharmaci_id")
    private Pharmaci pharmaci;


    @ManyToOne
    @JsonIgnore
    private Stock stock;

    public Medicament() {
    }
}
