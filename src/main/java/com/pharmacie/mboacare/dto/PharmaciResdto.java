package com.pharmacie.mboacare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PharmaciResdto {
    private String idPharmaci;
    private String nom;
    private String ville;
    private String quartier;
    private String email;
    private String contact;



}