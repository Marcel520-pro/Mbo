package com.pharmacie.mboacare.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StockReqdto {

    @NotNull(message = "veuillez remplir ce champ")

    private int quantite;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String nom;






}
