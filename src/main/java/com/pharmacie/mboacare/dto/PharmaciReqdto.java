package com.pharmacie.mboacare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor

public class PharmaciReqdto {


    @NotEmpty(message = "veuillez remplir ce champ")

    private String nom;

    @NotEmpty(message = "veuillez remplir ce champ")
    @Email(message = "cette email est erone")
    private String email;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String ville ;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String quartier ;

    @NotEmpty(message = "veuillez remplir ce champ")

    private String contact;




}

