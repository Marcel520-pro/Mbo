package com.pharmacie.mboacare.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MedicamentResdto {

    private String idMedicament;
    private String nom;
    private String forme;

    public MedicamentResdto(String idMedicament, String nom, String forme) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.forme = forme;


    }
}
