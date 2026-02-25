package com.pharmacie.mboacare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDICAMENT")


public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String idMedicament;
    private String nom;
    private String forme;


    @ManyToOne
    @JoinColumn(name = "pharmaci_id")
    private Pharmaci pharmaci;


    @ManyToOne
    private Stock stock;




}
